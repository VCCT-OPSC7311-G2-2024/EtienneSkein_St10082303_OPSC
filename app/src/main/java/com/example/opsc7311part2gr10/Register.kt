package com.example.opsc7311part2gr10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Firebase
        val auth = FirebaseAuth.getInstance()


        // Initialize an empty mutable map to store username and password
        val credentials = mutableMapOf<String, String>()

        val text_login = findViewById<TextView>(R.id.login_text)
        val btnRegister = findViewById<Button>(R.id.btn_register)
        val emailText = findViewById<EditText>(R.id.etEmail)
        val passwordText = findViewById<EditText>(R.id.etPassword)

        btnRegister.setOnClickListener {
            val email = emailText.text.toString().trim()
            val password = passwordText.text.toString().trim()

            // Check if the fields are not empty
            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Registration successful
                            val user = auth.currentUser
                            // Create an intent to start the next activity
                            val intent = Intent(this, HomePage::class.java).apply {
                                putExtra("user", user)
                            }
                            startActivity(intent)
                        } else {
                            // Registration failed
                            Toast.makeText(baseContext, "Registration failed.", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                // Notify the user that fields cannot be empty
                Toast.makeText(this, "Username and Password cannot be empty", Toast.LENGTH_LONG).show()
            }
        }

        text_login.setOnClickListener {
            // Code to navigate back to the main activity
            val intent = Intent(this, MainActivity::class.java).apply {}
            startActivity(intent)
        }
    }
}
