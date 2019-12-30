package de.outfittery.customerreportingworker.controller

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import de.outfittery.customerreportingworker.config.QueueConfig
import de.outfittery.customerreportingworker.model.*
import mu.KLogging
import org.springframework.amqp.AmqpRejectAndDontRequeueException
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty("hibernate-events.enabled")
class HibernateEventAdapter(private val opportunityController: OpportunityController,
                            private val customerController: CustomerController,
                            private val profileController: ProfileController,
                            private val orderController: OrderController,
                            private val subscriptionController: SubscriptionController,
                            private val objectMapper: ObjectMapper) {

    @RabbitListener(queues = [QueueConfig.HIBERNATE_QUEUE], concurrency = "5-20")
    fun handle(message: String) {
        try {
            logger.info(message)
            objectMapper.readTree(message)?.also { json ->
                json.get("entityClass")?.takeUnless { it.isNull }?.asText()
                        ?.also { entityClass ->
                            logger.info(entityClass)
                            json.get("state")?.also {
                                handleEvent(entityClass, json.toEntityId(), it)
                            }
                        }

            }
        } catch (e: Exception) {
            logger.warn("Failed to handle rabbit event", e)
        }
    }

    private fun handleEvent(entityClass: String, entityId: String, stateJsonNode: JsonNode) {
        try {
            when (entityClass) {

                "com.ps.opportunity.domain.Opportunity" -> objectMapper.treeToValue(stateJsonNode, Opportunity::class.java)
                        ?.also { opportunityController.putOpportunity(entityId, it) }

                "com.ps.opportunity.domain.OpportunityEvent" ->
                    objectMapper.treeToValue(stateJsonNode, OpportunityEvent::class.java)
                            ?.also { opportunityController.putOpportunityEvent(entityId, it) }

                "com.ps.customer.Customer" -> objectMapper.treeToValue(stateJsonNode, Customer::class.java)
                        ?.also { customerController.putCustomer(entityId, it) }

                "com.ps.customer.Profile" -> objectMapper.treeToValue(stateJsonNode, Profile::class.java)
                        ?.also { profileController.putProfile(entityId, it) }

                "com.ps.customer.order.Order" -> objectMapper.treeToValue(stateJsonNode, Order::class.java)
                        ?.also { orderController.putOrder(entityId, it) }

                "com.ps.opportunity.domain.SubscriptionEvent" -> objectMapper.treeToValue(stateJsonNode, SubscriptionEvent::class.java)
                        ?.also { subscriptionController.putSubscriptionEvent(entityId, it) }

                /*
                stylist event
                address event
                customer image?
                deactivation reason?
                fresh desk conversation?
                segment data?
                 */


                else -> logger.info("Event ignored: $entityClass")
            }
        } catch (e: Exception) {
            throw AmqpRejectAndDontRequeueException(e)
        }
    }

    companion object : KLogging()

}

private fun JsonNode.toEntityId(): String = this.get("entityId")?.asText() ?: throw NoEntityIdException()

class NoEntityIdException : RuntimeException()
