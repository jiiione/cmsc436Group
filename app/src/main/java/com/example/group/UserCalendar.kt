package com.example.group

import android.content.Intent
import android.os.Bundle
import android.widget.CalendarView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserCalendar  : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var calendarView: CalendarView
    private lateinit var percentageTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        progressBar = findViewById(R.id.progressBar)
        calendarView = findViewById(R.id.calendarView)
        percentageTV = findViewById(R.id.percentageTV);

        val totalItems = getTotalItems()
        val checkedItems = getCheckedItems()
        val percentage = ((checkedItems.toFloat() / totalItems) * 100).toInt()

        percentageTV.text = percentage.toString() + "%"
        progressBar.progress = percentage

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val intent = Intent(
                this@UserCalendar,
                ToDoList::class.java
            )
            intent.putExtra("year", year)
            intent.putExtra("month", month + 1)
            intent.putExtra("day", dayOfMonth)
            startActivity(intent)
        }
    }

    /*****************  hardcoded , remove later ****************/
    private fun getTotalItems(): Int {
        return 7
    }

    private fun getCheckedItems(): Int {
        return 2
    }

}

