package de.outfittery.customerreportingworker.repo

import de.outfittery.customerreportingworker.model.SubscriptionEvent
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface SubscriptionEventRepo : CrudRepository<SubscriptionEvent, String> {
    fun findByMetaCustomerId(customerId: String): List<SubscriptionEvent>
}