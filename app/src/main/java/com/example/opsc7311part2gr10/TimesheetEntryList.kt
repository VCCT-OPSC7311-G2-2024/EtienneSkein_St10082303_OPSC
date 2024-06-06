package com.example.opsc7311part2gr10

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opsc7311part2gr10.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.opsc7311part2gr10.EntryData

class TimesheetEntryList : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TimesheetEntryAdapter
    private lateinit var entries: MutableList<EntryData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timesheet_entry_list)

        // Initialize RecyclerView and adapter
        recyclerView = findViewById(R.id.recyclerView)
        entries = mutableListOf()
        adapter = TimesheetEntryAdapter(entries)

        // Set layout manager and adapter to RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Retrieve timesheet entries from Firestore
        fetchTimesheetEntries()
    }

    private fun fetchTimesheetEntries() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            val db = FirebaseFirestore.getInstance()
            db.collection("users")
                .document(userId)
                .collection("timesheetEntries")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        val entryData = document.toObject(EntryData::class.java)
                        entries.add(entryData)
                    }
                    adapter.notifyDataSetChanged()
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting documents.", exception)
                }
        }
    }
}
