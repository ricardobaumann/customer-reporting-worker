package de.outfittery.customerreportingworker

import de.outfittery.customerreportingworker.repo.CustomerRepo
import de.outfittery.customerreportingworker.service.CustomerReportingService
import mu.KLogging
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class Init(private val customerRepo: CustomerRepo,
           private val customerReportingService: CustomerReportingService) : CommandLineRunner {

    companion object : KLogging()

    override fun run(vararg args: String?) {

        /*
         customerRepo.findAll()
                 .forEach { customer ->
                     customer.id
                             ?.let { customerReportingService.loadTmViewByCustomerId(it) }
                             ?.also { logger.info("Result: $it") }
                 }
         */
    }
}