package de.outfittery.customerreportingworker.controller

import de.outfittery.customerreportingworker.model.Order
import de.outfittery.customerreportingworker.service.OrderService
import mu.KLogging
import org.springframework.stereotype.Component

@Component
class OrderController(private val orderService: OrderService) {

    companion object : KLogging()

    fun putOrder(entityId: String, order: Order) {
        try {
            orderService.indexOrder(entityId, order)
        } catch (e: Exception) {
            logger.warn("Failed to handle order event $order", e)
        }
    }
}