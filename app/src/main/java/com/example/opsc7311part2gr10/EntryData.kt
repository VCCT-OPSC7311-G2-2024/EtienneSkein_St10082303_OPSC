package com.example.opsc7311part2gr10

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class EntryData(
    val image: String? = null,
    val category: String = "",
    val date: String = "",
    val time: String = "",
    val description: String = ""
) {
    // Empty constructor required by Firestore
    constructor() : this("", "", "", "", "")
}
