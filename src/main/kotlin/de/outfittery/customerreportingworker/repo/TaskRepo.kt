package de.outfittery.customerreportingworker.repo

import de.outfittery.customerreportingworker.model.StylistTask
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepo : CrudRepository<StylistTask, String> {
    fun findByCustomerId(customerId: String): List<StylistTask>
}