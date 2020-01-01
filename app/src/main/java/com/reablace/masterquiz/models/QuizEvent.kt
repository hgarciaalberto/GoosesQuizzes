package com.reablace.masterquiz.models

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.google.firebase.firestore.GeoPoint
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@Suppress("UNCHECKED_CAST")

data class QuizEvent(
//    var answers: QuerySnapshot? = null,  // Por que respuestas en un evento? Las respustas deberían ir con los quizzes
    var date: Date? = null,
    var difficulty: Float? = null,
    var location: GeoPoint? = null,
    var name: String = "",
    var playersJoined: ArrayList<String> = ArrayList(),
    var playersScore: Map<String, Int> = HashMap(),
    var quizzes: ArrayList<String> = ArrayList(),
    var state: String = "",
    var tenancyId: String = ""
) : Serializable {

    fun getAddressFromGoogle(context: Context): String? {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses: List<Address>

        val lat = location?.latitude ?: 0.0
        val lon = location?.longitude ?: 0.0

        addresses = geocoder.getFromLocation(lat, lon, 1)

        return if (addresses.isNotEmpty() && addresses[0].getAddressLine(0).isNotBlank())
            addresses[0].getAddressLine(0)
        else
            ""
/*
            return if (addresses.isNotEmpty()) {
                // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                val address: String = addresses[0].getAddressLine(0)

                val city: String = addresses[0].locality
                val state: String = addresses[0].adminArea
                val country: String = addresses[0].countryName
                val postalCode: String = addresses[0].postalCode
                val knownName: String? = addresses[0].featureName // Only if available else return NULL
                knownName ?: "$address, $city, $country, $postalCode"
            }else{
                ""
            }
    */
    }
}


/* {
    //noinspection unchecked
    constructor(snapshot: DocumentSnapshot) : this(
        date = snapshot.getDate("date"),
        location = snapshot.getGeoPoint("location"),
        name = snapshot.getString("name") ?: "",
        playersScore = snapshot["playersScore"] as Map<String, Int>,
        quizzes = snapshot["quizzes"] as List<String>,
        state = snapshot.getString("state") ?: "",
        tenancyId = snapshot.getString("tenancyId") ?: ""
    )
}*/

