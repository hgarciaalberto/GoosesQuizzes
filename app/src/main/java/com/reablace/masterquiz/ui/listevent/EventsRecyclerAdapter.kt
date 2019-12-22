package com.reablace.masterquiz.ui.listevent

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.reablace.masterquiz.R
import com.reablace.masterquiz.models.QuizEvent
import java.util.*

class EventsRecyclerAdapter(options: FirestoreRecyclerOptions<QuizEvent>) :
    FirestoreRecyclerAdapter<QuizEvent, EventsRecyclerAdapter.ViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_list_item, parent, false)
        return ViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: QuizEvent) {
        holder.bind(model)
    }

    class ViewHolder(itemView: View, val context: Context) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.findViewById(R.id.title) as TextView
        private val address = itemView.findViewById(R.id.address) as TextView
        private val state = itemView.findViewById(R.id.state) as TextView

        fun bind(model: QuizEvent) {
            var addressFound = getAddressFromGoogle(
                model.location?.latitude ?: 0.0,
                model.location?.longitude ?: 0.0) ?: ""

            address.visibility = if (addressFound.isBlank()) View.GONE else View.VISIBLE

            title.text = model.name
            address.text = addressFound
            state.text = model.state
        }

        private fun getAddressFromGoogle(lat: Double, lon: Double): String? {
            val geocoder = Geocoder(context, Locale.getDefault())
            val addresses: List<Address>

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

    override fun onDataChanged() {
        // If there are no chat messages, show a view that invites the user to add a message.
//        mEmptyListMessage.setVisibility(if (itemCount == 0) View.VISIBLE else View.GONE)
    }


}
