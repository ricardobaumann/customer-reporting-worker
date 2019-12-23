package de.outfittery.customerreportingworker.repo

import de.outfittery.customerreportingworker.model.Opportunity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OpportunityRepo : CrudRepository<Opportunity, String> {
    fun findByCustomerId(customerId: String): List<Opportunity>
}