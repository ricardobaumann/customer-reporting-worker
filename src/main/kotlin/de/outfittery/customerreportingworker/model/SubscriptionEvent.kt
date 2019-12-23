package de.outfittery.customerreportingworker.model

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "subscription_event")
data class SubscriptionEvent(
        @Id var id: String? = null,
        val customerId: Long? = null,
        val eventType: String? = null,
        val meta: SubscriptionEventMeta? = null,
        val subscriptionId: Long? = null
)

data class SubscriptionEventMeta(
        val id: Long? = null,
        val customerId: Long? = null,
        val planCode: String? = null,
        val currentOpportunityId: Long? = null,
        val actorRole: String? = null
)