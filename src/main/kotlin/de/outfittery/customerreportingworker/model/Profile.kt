package de.outfittery.customerreportingworker.model

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

@Document(indexName = "profile")
data class Profile(
        @Id var id: String? = null,
        val customerId: String? = null,
        val dateOfBirth: String? = null,
        val shirtSize: String? = null,
        val trousersSizeLength: String? = null,
        val trousersSizeWidth: String? = null,
        val shoeSize: String? = null,
        val heightInCm: Int? = null,
        val weightInKg: Int? = null,
        val phoneNumber: String? = null,
        val preferredLanguage: String? = null,
        val newsletterAccepted: Boolean? = false,
        val subscribedToNewsletter: Boolean? = false,
        val spendingBudgetForShirtsFrom: Int? = null,
        val spendingBudgetForShirtsTo: Int? = null,
        val absoluteBudgetMinimumForShirts: Int? = null,
        val absoluteBudgetMaximumForShirts: Int? = null,
        val defaultBudgetForShirts: Int? = null,
        val spendingBudgetForJeansFrom: Int? = null,
        val spendingBudgetForJeansTo: Int? = null,
        val absoluteBudgetMinimumForJeans: Int? = null,
        val absoluteBudgetMaximumForJeans: Int? = null,
        val defaultBudgetForJeans: Int? = null,
        val spendingBudgetForShoesFrom: Int? = null,
        val spendingBudgetForShoesTo: Int? = null,
        val absoluteBudgetMinimumForShoes: Int? = null,
        val absoluteBudgetMaximumForShoes: Int? = null,
        val defaultBudgetForShoes: Int? = null,
        val spendingBudgetForSakkosFrom: Int? = null,
        val spendingBudgetForSakkosTo: Int? = null,
        val absoluteBudgetMinimumForSakkos: Int? = null,
        val absoluteBudgetMaximumForSakkos: Int? = null,
        val defaultBudgetForSakkos: Int? = null,
        val favoriteJeansBrand: String? = null,
        val favoriteJeansFit: String? = null,
        val dateCreated: String? = null,
        val lastUpdated: String? = null,
        val subscribedToColdCall: Boolean = true,
        val measurementUnit: String?,
        val trousersSizeWidthLocal: String? = null
)