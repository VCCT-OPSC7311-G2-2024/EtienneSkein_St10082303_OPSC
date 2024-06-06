package com.example.opsc7311part2gr10

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class GraphActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

        val backbutton = findViewById<Button>(R.id.btnBack)

        backbutton.setOnClickListener {
            val intent = Intent(this, HomePage::class.java).apply {
            }
            startActivity(intent)
        }
        // The XML layout contains your custom GraphView
        // It's already defined in the layout XML file, so no need to programmatically add it
    }
}
