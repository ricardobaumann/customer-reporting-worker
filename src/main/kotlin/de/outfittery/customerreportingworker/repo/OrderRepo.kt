package de.outfittery.customerreportingworker.repo

import de.outfittery.customerreportingworker.model.Order
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepo : CrudRepository<Order, String> {
    fun findByCustomer(customerId: String): List<Order>
}