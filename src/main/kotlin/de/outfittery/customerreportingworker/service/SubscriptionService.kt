package de.outfittery.customerreportingworker.service

import de.outfittery.customerreportingworker.model.SubscriptionEvent
import de.outfittery.customerreportingworker.repo.SubscriptionEventRepo
import org.springframework.stereotype.Service

@Service
class SubscriptionService(private val subscriptionEventRepo: SubscriptionEventRepo) {

    fun indexSubscriptionEvent(entityId: String, subscriptionEvent: SubscriptionEvent) {
        subscriptionEventRepo.save(subscriptionEvent.apply { this.id = entityId })
    }

}
