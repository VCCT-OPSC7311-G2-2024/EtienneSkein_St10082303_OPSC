package com.example.opsc7311part2gr10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth


class HomePage : AppCompatActivity() {


     private lateinit var drawerToggle: ActionBarDrawerToggle


     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

         val username = findViewById<TextView>(R.id.username)
         val auth = FirebaseAuth.getInstance()
         val userEmail = auth.currentUser?.email
         val userName = userEmail?.substringBefore('@') ?: "Unknown User"
         username.text = "Welcome, $userName"

         val buttonViewEntries = findViewById<Button>(R.id.btnViewEntries)
         val buttonAddCategory = findViewById<Button>(R.id.btnAddCategory)
         val buttonHourGoal = findViewById<Button>(R.id.btnMinandMax)
         val buttonTimesheetEntry = findViewById<Button>(R.id.btnTimesheet)
         val buttonLogOut = findViewById<Button>(R.id.btnLogOut)
         val buttonGraph = findViewById<Button>(R.id.btnGraph)

         buttonGraph.setOnClickListener{
             val intent = Intent(this, GraphActivity::class.java).apply {
             }
             startActivity(intent)
         }

         buttonViewEntries.setOnClickListener{
             val intent = Intent(this, TimesheetEntryList::class.java).apply {
                 val username = intent.getStringExtra("username")
                 val password = intent.getStringExtra("password")
                 putExtra("username", username)
                 putExtra("password", password)
             }
             startActivity(intent)
         }

         buttonAddCategory.setOnClickListener{
             val intent = Intent(this, Category::class.java).apply {
                 val username = intent.getStringExtra("username")
                 val password = intent.getStringExtra("password")
                 putExtra("username", username)
                 putExtra("password", password)
             }
             startActivity(intent)
         }

         buttonHourGoal.setOnClickListener{
             val intent = Intent(this, SeekBar::class.java).apply {
                 val username = intent.getStringExtra("username")
                 val password = intent.getStringExtra("password")
                 putExtra("username", username)
                 putExtra("password", password)
             }
             startActivity(intent)
         }

         buttonTimesheetEntry.setOnClickListener{
             val intent = Intent(this, Timesheet::class.java).apply {
                 val username = intent.getStringExtra("username")
                 val password = intent.getStringExtra("password")
                 val categories = intent.getStringArrayListExtra("categories")
                 putExtra("username", username)
                 putExtra("password", password)
                 putExtra("categories", categories)
             }
             startActivity(intent)
         }

         buttonLogOut.setOnClickListener{
             val intent = Intent(this, MainActivity::class.java).apply {
                 putExtra("username", intent.getStringExtra("username"))
                 putExtra("password", intent.getStringExtra("password"))
             }
             startActivity(intent)
         }





         }



     }
