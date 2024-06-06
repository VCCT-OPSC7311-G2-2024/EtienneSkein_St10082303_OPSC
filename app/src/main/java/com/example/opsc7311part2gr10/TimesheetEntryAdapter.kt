package com.example.opsc7311part2gr10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.opsc7311part2gr10.EntryData
import com.example.opsc7311part2gr10.R

class TimesheetEntryAdapter(private val entries: List<EntryData>) :
    RecyclerView.Adapter<TimesheetEntryAdapter.EntryViewHolder>() {

    class EntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryTextView: TextView = itemView.findViewById(R.id.categoryTextView)
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val timeTextView: TextView = itemView.findViewById(R.id.timeTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_timesheet_entry, parent, false)
        return EntryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val currentEntry = entries[position]
        holder.categoryTextView.text = "Category: ${currentEntry.category}"
        holder.dateTextView.text = "Date: ${currentEntry.date}"
        holder.timeTextView.text = "Time: ${currentEntry.time}"
        holder.descriptionTextView.text = "Description: ${currentEntry.description}"
    }

    override fun getItemCount(): Int {
        return entries.size
    }
}
