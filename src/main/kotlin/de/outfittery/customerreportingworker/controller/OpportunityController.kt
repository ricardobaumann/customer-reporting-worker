package de.outfittery.customerreportingworker.controller

import de.outfittery.customerreportingworker.model.Opportunity
import de.outfittery.customerreportingworker.model.OpportunityEvent
import de.outfittery.customerreportingworker.service.OpportunityService
import mu.KLogging
import org.springframework.stereotype.Component

@Component
class OpportunityController(private val opportunityService: OpportunityService) {

    fun putOpportunity(entityId: String, opportunity: Opportunity) {
        logger.info("Handling opportunity: $opportunity")
        opportunityService.indexOpportunity(opportunity.apply { opportunityId = entityId })
    }

    fun putOpportunityEvent(entityId: String, opportunityEvent: OpportunityEvent) {
        try {
            logger.info("Handling opportunity event: $opportunityEvent")
            opportunityService.indexOpportunityEvent(opportunityEvent.apply { this.id = entityId })
        } catch (e: Exception) {
            logger.warn("Opportunity event $opportunityEvent not indexed due to", e)//TODO dead letter queue?
        }
    }


    companion object : KLogging()
}

