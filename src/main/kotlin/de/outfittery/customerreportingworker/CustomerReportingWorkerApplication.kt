package de.outfittery.customerreportingworker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.retry.annotation.EnableRetry

@EnableRetry
@SpringBootApplication
class CustomerReportingWorkerApplication

fun main(args: Array<String>) {
    runApplication<CustomerReportingWorkerApplication>(*args)
}
