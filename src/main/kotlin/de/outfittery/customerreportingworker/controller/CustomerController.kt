package de.outfittery.customerreportingworker.controller

import de.outfittery.customerreportingworker.model.Customer
import de.outfittery.customerreportingworker.repo.CustomerRepo
import mu.KLogging
import org.springframework.stereotype.Component

@Component
class CustomerController(private val customerRepo: CustomerRepo) {
    fun putCustomer(entityId: String, customer: Customer) {
        logger.info("Handling customer $customer")
        customerRepo.save(customer.apply { this.id = entityId })
    }

    companion object : KLogging()
}

