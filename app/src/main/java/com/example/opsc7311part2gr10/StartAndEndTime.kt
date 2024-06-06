package com.example.opsc7311part2gr10

import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class StartAndEndTime : AppCompatActivity() {

    var startTimeFormate = SimpleDateFormat("hh:mm a", Locale.UK)
    var endTimeFormate = SimpleDateFormat("hh:mm a", Locale.UK)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_and_end_time)

        val buttonStartTime = findViewById<Button>(R.id.btnTimeStart)
        val buttonEndTime = findViewById<Button>(R.id.btnTimeEnd)
        val buttonHomeTime = findViewById<Button>(R.id.btnDoneNext)
        val viewStartTime = findViewById<TextView>(R.id.tvShowStartTime)
        val viewEndTime = findViewById<TextView>(R.id.tvShowEndTime)



        buttonStartTime.setOnClickListener{
            val now = Calendar.getInstance()
            val timePicker = TimePickerDialog(this,
                TimePickerDialog.OnTimeSetListener{ view, hourOfDay, minute->
                val selectedStartTime = Calendar.getInstance()
                selectedStartTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedStartTime.set(Calendar.MINUTE, minute)
                    viewStartTime.text = startTimeFormate.format(selectedStartTime.time)
            },
                now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false)
            timePicker.show()

        }
        buttonEndTime.setOnClickListener{

            val now = Calendar.getInstance()
            val timePicker = TimePickerDialog(this,
                TimePickerDialog.OnTimeSetListener{ view, hourOfDay, minute->
                val selectedEndTime = Calendar.getInstance()
                selectedEndTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
                selectedEndTime.set(Calendar.MINUTE, minute)
                    viewEndTime.text = endTimeFormate.format(selectedEndTime.time)
            },
                now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false)
            timePicker.show()
        }

        buttonHomeTime.setOnClickListener {
            val Intent = Intent(this, HomePage::class.java)
            startActivity(Intent)
        }






    }
}