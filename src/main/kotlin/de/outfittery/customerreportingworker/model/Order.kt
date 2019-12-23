package de.outfittery.customerreportingworker.model

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "order")
data class Order(
        @Id var orderId: String? = null,
        val currencyCode: String? = null,
        val customer: Long? = null,
        val dateCreated: String? = null,
        val gtcAccepted: Boolean? = null,
        val isInPayedToleranceZone: Boolean? = null,
        val lastUpdated: String? = null,
        val logisticsProcessor: String? = null,
        val maxTotalAmountBilledRetailGross: Double? = null,
        val maxTotalAmountBilledRetailGrossOrigin: String? = null,
        val migrationMergeFlag: Boolean? = null,
        val outfitRequested: Boolean? = null,
        val paymentMethod: Int? = null,
        val paymentState: Int? = null,
        val previewRequested: Boolean? = null,
        val referralAmount: Double? = null,
        val riskClass: String? = null,
        val riskClassSource: String? = null,
        val salesChannel: String? = null,
        val salesSubchannel: String? = null,
        val shippingAddress: Long? = null,
        val state: Int? = null,
        val stylelist: Long? = null,
        val totalAmountBasketPurchaseGross: Double? = null,
        val totalAmountBasketRetailGross: Double? = null,
        val totalAmountBilledDiscount: Double? = null,
        val totalAmountBilledDiscountAbsolute: Double? = null,
        val totalAmountBilledDiscountPercentage: Double? = null,
        val totalAmountBilledPurchaseGross: Double? = null,
        val totalAmountBilledRetailGross: Double? = null,
        val totalAmountOutstanding: Double? = null,
        val totalAmountPayed: Double? = null,
        val totalAmountReserved: Double? = null,
        val totalGoodwillCredit: Double? = null,
        val vatPercentage: Double? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Order

        if (orderId != other.orderId) return false

        return true
    }

    override fun hashCode(): Int {
        return orderId?.hashCode() ?: 0
    }
}