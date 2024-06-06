package com.example.opsc7311part2gr10

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class TheUserCalendarDate : AppCompatActivity() {

    var formate = SimpleDateFormat("dd MM, YYYY", Locale.UK)

    private lateinit var button: Button





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_the_user_calendar_date)

        val btnChooseDate = findViewById<Button>(R.id.btnDates)
        val viewTheDate = findViewById<TextView>(R.id.tvShowDate)
        val buttonHomeCalendar = findViewById<Button>(R.id.btnHomeCalendar)

        btnChooseDate.setOnClickListener{
            val now = Calendar.getInstance()
            val datePicker = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR, year)
                selectedDate.set(Calendar.MONTH, month)
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val date = formate.format(selectedDate.time)
                    viewTheDate.text = formate.format(selectedDate.time)

                Toast.makeText(this, "date : " + date, Toast.LENGTH_SHORT).show()
            },
                now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        buttonHomeCalendar.setOnClickListener {
            val Intent = Intent(this, HomePage::class.java)
            startActivity(Intent)
        }

    }
}