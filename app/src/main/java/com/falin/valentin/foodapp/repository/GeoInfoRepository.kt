package com.falin.valentin.foodapp.repository

import android.content.Context
import android.location.Geocoder
import com.google.android.gms.maps.model.Marker
import java.util.*

/**
 * Repository-layer class for work device location.
 *
 */
class GeoInfoRepository(private val context: Context) {

    /**
     * This method can be called for create pickup address.
     *
     * @param marker Selected marker
     *
     * @return Created marker address
     */
    fun getAddress(marker: Marker): String {
        var result = ""
        val geoLocation = Geocoder(context, Locale.getDefault()).getFromLocation(
            marker.position.latitude,
            marker.position.longitude,
            1
        )
        if (geoLocation != null && geoLocation.size > 0) {
            result = geoLocation[0].getAddressLine(0) +
                    if (geoLocation[0].postalCode != null) ", ${geoLocation[0].postalCode}" else ""
        }
        return result
    }
}