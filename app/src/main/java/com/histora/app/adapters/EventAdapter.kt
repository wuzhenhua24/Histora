package com.histora.app.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.histora.app.databinding.ItemEventCardBinding
import com.histora.app.models.HistoricalEvent

class EventAdapter(
    private val onEventClick: (HistoricalEvent) -> Unit
) : ListAdapter<HistoricalEvent, EventAdapter.EventViewHolder>(EventDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return EventViewHolder(binding, onEventClick)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class EventViewHolder(
        private val binding: ItemEventCardBinding,
        private val onEventClick: (HistoricalEvent) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(event: HistoricalEvent) {
            binding.eventTitle.text = event.title
            binding.eventDescription.text = event.description
            binding.eventDate.text = event.date
            binding.eventCategory.text = event.category

            binding.eventCard.setOnClickListener {
                onEventClick(event)
            }
        }
    }

    private class EventDiffCallback : DiffUtil.ItemCallback<HistoricalEvent>() {
        override fun areItemsTheSame(oldItem: HistoricalEvent, newItem: HistoricalEvent): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HistoricalEvent, newItem: HistoricalEvent): Boolean {
            return oldItem == newItem
        }
    }
}
