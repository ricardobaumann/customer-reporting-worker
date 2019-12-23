package de.outfittery.customerreportingworker.repo

import de.outfittery.customerreportingworker.model.Profile
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileRepo : CrudRepository<Profile, String> {
    fun findByCustomerId(customerId: String): Profile?
}