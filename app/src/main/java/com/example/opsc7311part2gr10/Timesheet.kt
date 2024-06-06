package com.example.opsc7311part2gr10

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.*
import com.google.android.gms.cast.framework.media.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.security.KeyStore.Entry
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.log


class Timesheet : AppCompatActivity() {

    var imagePicker :  ImageView ?= null
    private lateinit var button: Button
    private lateinit var imageView: ImageView


    companion object {
        val IMAGE_REQUEST_CODE = 100
      }

    val entries = ArrayList<EntryData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timesheet_entry)
        val firestoreDB = FirebaseFirestore.getInstance()


        val categories: ArrayList<String>? = intent.getStringArrayListExtra("categories")

        val spinner: Spinner = findViewById(R.id.spinner)

        // Check if the categories list is not null
        categories?.let {
            // Create an ArrayAdapter using the string array and a default spinner layout
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, it)

            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        val camera = findViewById<Button>(R.id.btnCamera)
        val buttonTimesheets = findViewById<Button>(R.id.btnHomeTimsheet)

        buttonTimesheets.setOnClickListener {
            val intent = Intent(this, HomePage::class.java).apply {
                putExtra("username", intent.getStringExtra("username"))
                putExtra("password", intent.getStringExtra("password"))
                putExtra("entries", intent.getStringArrayExtra("entries"))
            }
            startActivity(intent)
        }

        camera.setOnClickListener {
            var intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 123)
        }

        val btnSetDate: Button = findViewById(R.id.btn_set_date)

        // Set an OnClickListener on the button
        btnSetDate.setOnClickListener {
            // Open the DatePickerDialog
            showDatePickerDialog()
        }

        val btnSetTime: Button = findViewById(R.id.btn_set_time)

        // Set an OnClickListener on the button
        btnSetTime.setOnClickListener {
            // Open the TimePickerDialog
            showTimePickerDialog()
        }

        val btnAddEntry: Button = findViewById(R.id.btn_add_entry)
        btnAddEntry.setOnClickListener {
            addEntry()
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
       super.onActivityResult(requestCode, resultCode,data)
        if(requestCode == 123){
            var bmp:Bitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(bmp)

        }
       else if(requestCode == IMAGE_REQUEST_CODE)
       {
         imageView.setImageURI(data?.data)
        }

    }

    private fun showDatePickerDialog() {
        // Get current date to set as default in picker
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // DatePickerDialog instance
        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            // Format the selected date and set as button text
            val selectedDate = "${selectedDay}/${selectedMonth + 1}/$selectedYear"  // Month is 0-indexed, add 1 for correct display
            findViewById<Button>(R.id.btn_set_date).text = selectedDate
        }, year, month, day)

        // Show the dialog
        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {
        // Get current time to set as default in the picker
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        // TimePickerDialog instance
        val timePickerDialog = TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            // Format the selected time and set as button text
            val formattedTime = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute)
            findViewById<Button>(R.id.btn_set_time).text = formattedTime
        }, hour, minute, true) // 'true' for 24-hour view

        // Show the dialog
        timePickerDialog.show()
    }

    private fun addEntry() {
        val spinner: Spinner = findViewById(R.id.spinner)
        val btnSetDate: Button = findViewById(R.id.btn_set_date)
        val btnSetTime: Button = findViewById(R.id.btn_set_time)
        //val etDescription: EditText = findViewById(R.id.etDescription)

        // Assume you have a method to get the image URI if applicable
        val imageUri: String? = null  // Replace with actual method call if needed

        // Create the entry data object
        val entryData = hashMapOf(
            "image" to imageUri,
            "category" to spinner.selectedItem.toString(),
            "date" to btnSetDate.text.toString(),
            "time" to btnSetTime.text.toString(),
            "description" to "test"//etDescription.text.toString()
        )

        // Add the entry to Firestore
        val user = FirebaseAuth.getInstance().currentUser
        val firestoreDB = FirebaseFirestore.getInstance()
        user?.uid?.let { userId ->
            firestoreDB.collection("users")
                .document(userId)
                .collection("timesheetEntries")
                .add(entryData)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }
    }


}














