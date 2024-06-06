package com.example.opsc7311part2gr10

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class GraphView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val dataPoints = listOf(20, 50, 30, 80, 60) // Sample data points

    private val linePaint = Paint().apply {
        color = Color.RED
        strokeWidth = 5f
        isAntiAlias = true
        style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas?.apply {
            val width = width.toFloat()
            val height = height.toFloat()

            // Draw background
            drawColor(Color.WHITE)

            // Draw horizontal and vertical lines
            drawLine(100f, 100f, 100f, height - 100f, linePaint)
            drawLine(100f, height - 100f, width - 100f, height - 100f, linePaint)

            // Draw data points and lines
            val interval = (width - 200f) / (dataPoints.size - 1)
            var startX = 100f
            var startY = height - 100f - dataPoints.first()
            for (i in 1 until dataPoints.size) {
                val endX = startX + interval
                val endY = height - 100f - dataPoints[i]
                drawLine(startX, startY, endX, endY, linePaint)
                drawCircle(startX, startY, 8f, linePaint)
                startX = endX
                startY = endY
            }
        }
    }
}
