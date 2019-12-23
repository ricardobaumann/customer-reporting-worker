package de.outfittery.customerreportingworker.service

import de.outfittery.customerreportingworker.model.Opportunity
import de.outfittery.customerreportingworker.model.OpportunityEvent
import de.outfittery.customerreportingworker.repo.OpportunityEventRepo
import de.outfittery.customerreportingworker.repo.OpportunityRepo
import mu.KLogging
import org.springframework.data.repository.findByIdOrNull
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service

@Service
class OpportunityService(private val opportunityRepo: OpportunityRepo,
                         private val opportunityEventRepo: OpportunityEventRepo) {

    companion object : KLogging()

    fun indexOpportunity(opportunity: Opportunity) {
        opportunityRepo.save(opportunity)
    }

    @Retryable(backoff = Backoff(delay = 500), maxAttempts = 3)
    fun indexOpportunityEvent(opportunityEvent: OpportunityEvent) {
        opportunityEventRepo.save(
                opportunityEvent.apply {
                    this.customerId = opportunityEvent.id
                            ?.let {
                                opportunityRepo.findByIdOrNull(it)
                                        ?.customerId
                            } ?: throw OpportunityNotFound(opportunityEvent.opportunity)
                })
    }
}

class OpportunityNotFound(opportunity: String?) : RuntimeException(opportunity)
