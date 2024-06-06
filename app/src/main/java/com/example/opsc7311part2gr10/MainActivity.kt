package com.example.opsc7311part2gr10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text_register = findViewById<TextView>(R.id.register_text)
        val buttonLog = findViewById<Button>(R.id.btnLogin)
        val usernameText = findViewById<EditText>(R.id.etUsername)
        val passwordText = findViewById<EditText>(R.id.etPassword)

        val auth = FirebaseAuth.getInstance()

        buttonLog.setOnClickListener {

            val username = usernameText.text.toString().trim()
            val password = passwordText.text.toString().trim()

            if (username.isEmpty()) {
                usernameText.error = "Please enter username"
                usernameText.requestFocus()
            } else if (password.isEmpty()) {
                passwordText.error = "Please enter password"
                passwordText.requestFocus()
            } else {
                // Authenticate user using Firebase Authentication
                auth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            val user = auth.currentUser
                            val intent = Intent(this, HomePage::class.java)
                            intent.putExtra("user", user)
                            startActivity(intent)
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
        text_register.setOnClickListener {
            // Code to be executed when the TextView is clicked
            val Intent = Intent(this, Register::class.java)
            startActivity(Intent)
        }

    }
}


