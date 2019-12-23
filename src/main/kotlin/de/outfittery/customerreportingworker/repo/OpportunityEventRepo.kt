package de.outfittery.customerreportingworker.repo

import de.outfittery.customerreportingworker.model.OpportunityEvent
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OpportunityEventRepo : CrudRepository<OpportunityEvent, String> {
    fun findByCustomerId(customerId: String): List<OpportunityEvent>
}