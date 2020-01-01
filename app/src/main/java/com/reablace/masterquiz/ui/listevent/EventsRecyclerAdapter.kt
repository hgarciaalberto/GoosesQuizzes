package com.reablace.masterquiz.ui.listevent

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.reablace.masterquiz.R
import com.reablace.masterquiz.models.QuizEvent

private const val TAG = "EventsRecyclerAdapter"

class EventsRecyclerAdapter(
    options: FirestoreRecyclerOptions<QuizEvent>, private val clickListener: EventClickListener) :
    FirestoreRecyclerAdapter<QuizEvent, EventsRecyclerAdapter.ViewHolder>(options) {

    /**
     * Callback encharge of return the "eventId" clicked
     */
    interface EventClickListener {
        fun onEventClickListener(eventId: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_list_item, parent, false)
        return ViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: QuizEvent) {
        holder.bind(model)

        holder.itemView.setOnClickListener {
            val eventId = snapshots.getSnapshot(position).id
            Log.d(TAG, "Event name clicked: ${model.name}") //TODO: delete line
            clickListener.onEventClickListener(eventId)
        }
    }

    override fun onDataChanged() {
        // If there are no chat messages, show a view that invites the user to add a message.
//        mEmptyListMessage.setVisibility(if (itemCount == 0) View.VISIBLE else View.GONE)
    }


    /**
     * ViewHolder use to populate the view elements
     */
    class ViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.findViewById(R.id.title) as TextView
        private val address = itemView.findViewById(R.id.address) as TextView
        private val state = itemView.findViewById(R.id.state) as TextView

        fun bind(model: QuizEvent) {

            val addressFound = model.getAddressFromGoogle(context) ?: ""
            address.visibility = if (addressFound.isBlank()) View.GONE else View.VISIBLE

            title.text = model.name
            address.text = addressFound
            state.text = model.state
        }
    }
}
