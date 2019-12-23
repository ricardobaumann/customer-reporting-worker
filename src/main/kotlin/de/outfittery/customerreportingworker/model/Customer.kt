package de.outfittery.customerreportingworker.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "customer")
data class Customer(
        @Id var id: String? = null,
        val email: String? = null,
        val enabled: Boolean = true,
        val firstName: String? = null,
        val lastName: String? = null,
        val profile: Long? = null,
        val riskClass: String? = null,
        val shippingCountryCode: String? = null,
        @JsonProperty("stylelist") val stylistId: Long? = null
)