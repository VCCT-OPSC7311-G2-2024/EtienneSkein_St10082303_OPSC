package com.example.opsc7311part2gr10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener


class SeekBar : AppCompatActivity() {

    private lateinit var sliderMin :SeekBar
    private lateinit var valueMin :TextView

    private lateinit var sliderMax :SeekBar
    private lateinit var valueMax :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seek_bar)

        sliderMin = findViewById(R.id.sbSliderMin) as SeekBar
        valueMin = findViewById(R.id.tvMinumum) as TextView

        sliderMax = findViewById(R.id.sbSliderMax) as SeekBar
        valueMax = findViewById(R.id.tvMaximum) as TextView

        val buttonHomePageSeekBar = findViewById<Button>(R.id.btnHomeSeekbar)

        sliderMin.max = 50
        sliderMax.max = 50


        sliderMin.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                valueMin.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
//                valueMin.text = "Started at : " +seekBar.progress
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
//                valueMin.text = "Selected : " +seekBar.progress

            }
        })

        sliderMax.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(s0: SeekBar?, s1: Int, s2: Boolean) {
                valueMax.text = s1.toString()
            }

            override fun onStartTrackingTouch(s0: SeekBar?) {
            }

            override fun onStopTrackingTouch(s0: SeekBar?) {
            }
        })

        buttonHomePageSeekBar.setOnClickListener {
            val Intent = Intent(this, HomePage::class.java)
            startActivity(Intent)
        }

    }
}