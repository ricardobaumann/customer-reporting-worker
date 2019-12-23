package de.outfittery.customerreportingworker.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "opportunity")
data class Opportunity @JsonCreator constructor(
        @Id var opportunityId: String? = null,
        val email: String? = null,
        val firstName: String? = null,
        val lastName: String? = null,
        val riskClass: String? = null,
        val shippingCountryCode: String? = null,
        val customerId: String? = null,
        var currentState: String? = null,
        @JsonProperty("stylelist") val stylistId: Long? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Opportunity

        if (opportunityId != other.opportunityId) return false

        return true
    }

    override fun hashCode(): Int {
        return opportunityId?.hashCode() ?: 0
    }
}

@Document(indexName = "opportunity_event")
data class OpportunityEvent(
        @Id var id: String? = null,
        val eventType: String? = null,
        val opportunity: String? = null,
        var customerId: String? = null
)