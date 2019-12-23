package de.outfittery.customerreportingworker.controller

import de.outfittery.customerreportingworker.service.CustomerReportingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CustomerReportingController(private val customerReportingService: CustomerReportingService) {

    @GetMapping("/tm/customers/{customerId}")
    fun getTmCustomerReport(@PathVariable customerId: String) = customerReportingService.loadTmViewByCustomerId(customerId)

}