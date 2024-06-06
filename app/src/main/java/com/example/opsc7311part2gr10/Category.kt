package com.example.opsc7311part2gr10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class Category : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val categories = ArrayList<String>()

        val btnAddCategory = findViewById<Button>(R.id.btnAdd)

        val category_text = findViewById<EditText>(R.id.etCategory)

        val buttonHomeEntry = findViewById<Button>(R.id.btnHomeCategory)

        btnAddCategory.setOnClickListener{
            val cat_text = category_text.text
            categories.add(cat_text.toString())
        }

        buttonHomeEntry.setOnClickListener {
            val intent = Intent(this, HomePage::class.java).apply {
                putExtra("username", intent.getStringExtra("username"))
                putExtra("password", intent.getStringExtra("password"))
                putExtra("categories", categories)
            }
            startActivity(intent)
        }
    }
}