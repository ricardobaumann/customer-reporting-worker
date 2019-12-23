package de.outfittery.customerreportingworker.service

import de.outfittery.customerreportingworker.model.*
import de.outfittery.customerreportingworker.repo.*
import de.outfittery.customerreportingworker.service.ResultType.*
import mu.KLogging
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.function.Supplier

@Service
class CustomerReportingService(private val customerRepo: CustomerRepo,
                               private val profileRepo: ProfileRepo,
                               private val opportunityRepo: OpportunityRepo,
                               private val opportunityEventRepo: OpportunityEventRepo,
                               private val subscriptionEventRepo: SubscriptionEventRepo,
                               private val taskRepo: TaskRepo,
                               private val orderRepo: OrderRepo,
                               private val executorService: ExecutorService) {

    companion object : KLogging()

    private val loadFunctions = listOf(
            { customerId: String -> ResultUnit(type = CUSTOMER, result = customerRepo.findByIdOrNull(customerId)) },
            { customerId: String -> ResultUnit(type = PROFILE, result = profileRepo.findByCustomerId(customerId)) },
            { customerId: String -> ResultUnit(type = OPPORTUNITY, result = opportunityRepo.findByCustomerId(customerId)) },
            { customerId: String -> ResultUnit(type = OPPORTUNITY_EVENT, result = opportunityEventRepo.findByCustomerId(customerId)) },
            { customerId: String -> ResultUnit(type = SUBSCRIPTION_EVENT, result = subscriptionEventRepo.findByMetaCustomerId(customerId)) },
            { customerId: String -> ResultUnit(type = ORDER, result = orderRepo.findByCustomer(customerId)) },
            { customerId: String -> ResultUnit(type = ORDER, result = taskRepo.findByCustomerId(customerId)) }
    )

    @Suppress("UNCHECKED_CAST")
    fun loadTmViewByCustomerId(customerId: String) = mutableMapOf<ResultType, Any?>().also { map ->
        CompletableFuture.allOf(
                *loadFunctions.map {
                    CompletableFuture.supplyAsync(Supplier {
                        it.invoke(customerId)
                                .also { result ->
                                    map[result.type] = result.result
                                }
                    }, executorService)
                }.toTypedArray()
        ).join()
    }.let { results ->
        logger.info(results.toString())
        TmCustomerReport(
                customer = results[CUSTOMER] as Customer?,
                profile = results[PROFILE] as Profile?,
                tasks = results[TASK]?.let { it as List<StylistTask> } ?: listOf(),
                orders = results[ORDER]?.let { it as List<Order> } ?: listOf(),
                opportunities = buildOpportunityReport(results[OPPORTUNITY] as List<Opportunity>?, results[OPPORTUNITY_EVENT] as List<OpportunityEvent>?),
                subscriptionEvents = results[SUBSCRIPTION_EVENT]?.let { it as List<SubscriptionEvent> } ?: listOf()
        )
    }

    private fun buildOpportunityReport(opportunities: List<Opportunity>?, events: List<OpportunityEvent>?): List<OpportunityReport> {
        return opportunities?.map { op ->
            OpportunityReport(
                    opportunity = op,
                    events = events?.filter { it.opportunity == op.opportunityId } ?: listOf()
            )
        } ?: listOf()
    }

}

enum class ResultType {
    CUSTOMER, PROFILE, SUBSCRIPTION_EVENT, OPPORTUNITY, OPPORTUNITY_EVENT, ORDER, TASK
}

data class ResultUnit(
        val type: ResultType,
        val result: Any? = null
)

data class TmCustomerReport(
        val customer: Customer?,
        val profile: Profile? = null,
        val orders: List<Order> = listOf(),
        val opportunities: List<OpportunityReport> = listOf(),
        val subscriptionEvents: List<SubscriptionEvent> = listOf(),
        val tasks: List<StylistTask> = listOf()
)

data class OpportunityReport(
        val opportunity: Opportunity,
        val events: List<OpportunityEvent> = listOf()
)