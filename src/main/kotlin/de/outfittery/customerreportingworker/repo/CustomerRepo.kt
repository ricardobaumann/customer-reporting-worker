package de.outfittery.customerreportingworker.repo

import de.outfittery.customerreportingworker.model.Customer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepo : CrudRepository<Customer, String>