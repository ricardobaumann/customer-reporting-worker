package de.outfittery.customerreportingworker.controller

import de.outfittery.customerreportingworker.model.Profile
import de.outfittery.customerreportingworker.repo.ProfileRepo
import mu.KLogging
import org.springframework.stereotype.Component

@Component
class ProfileController(private val profileRepo: ProfileRepo) {

    companion object : KLogging()

    fun putProfile(entityId: String, profile: Profile) {
        profileRepo.save(profile.apply { id = entityId })
    }
}