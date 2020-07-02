package com.falin.valentin.foodapp.models.ui

/**
 * This is a data class for work with [MapActivity] and display Pickup Info.
 *
 * @param name display pickup name
 * @param workingHours display working hours
 * @param address display marker address
 * @param distance Display distance between current location and selected Marker
 * @property LOCATION_ERROR_CODE = If distance id incorrect display this code
 */
data class PickupInfoUi(
    var name: String = "",
    var workingHours: String = "",
    var address: String = "",
    var distance: Int = LOCATION_ERROR_CODE
) {
    companion object {
        const val LOCATION_ERROR_CODE = -1
    }
}