package de.outfittery.customerreportingworker.service

import de.outfittery.customerreportingworker.model.Order
import de.outfittery.customerreportingworker.repo.OrderRepo
import mu.KLogging
import org.springframework.stereotype.Service

@Service
class OrderService(private val orderRepo: OrderRepo) {

    companion object : KLogging()

    fun indexOrder(entityId: String, order: Order) {

        orderRepo.save(order.apply { this.orderId = entityId })

    }
}