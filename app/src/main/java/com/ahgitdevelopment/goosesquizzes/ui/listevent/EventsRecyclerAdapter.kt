package com.ahgitdevelopment.goosesquizzes.ui.listevent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ahgitdevelopment.goosesquizzes.models.Event
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class EventsRecyclerAdapter(val options: FirestoreRecyclerOptions<Event>) :
    FirestoreRecyclerAdapter<Event, EventsRecyclerAdapter.ViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, model: Event) {
        holder.bind(model)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById(android.R.id.text1) as TextView

        fun bind(model: Event) {
            title.text = model.title
        }
    }

    override fun onDataChanged() {
        // If there are no chat messages, show a view that invites the user to add a message.
//        mEmptyListMessage.setVisibility(if (itemCount == 0) View.VISIBLE else View.GONE)
    }


}
