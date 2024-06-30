package com.example.group

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var loginBT: Button
    private lateinit var usernameET: EditText
    private lateinit var pwdET: EditText

    //    private lateinit var progressBar: ProgressBar
//    private lateinit var calendarView: CalendarView
//    private lateinit var percentageTV: TextView
//
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginBT = findViewById(R.id.loginBT)

        loginBT.setOnClickListener() {
            val intent = Intent(
                this@MainActivity,
                UserCalendar::class.java
            )
            startActivity(intent)
        }

//
//        progressBar = findViewById(R.id.progressBar)
//        calendarView = findViewById(R.id.calendarView)
//        percentageTV = findViewById(R.id.percentageTV);
//
//        val totalItems = getTotalItems()
//        val checkedItems = getCheckedItems()
//        val percentage = ((checkedItems.toFloat() / totalItems) * 100).toInt()
//
//        percentageTV.text = percentage.toString() + "%"
//        progressBar.progress = percentage
//
//        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
//            val intent = Intent(
//                this@MainActivity,
//                ToDoList::class.java
//            )
//            intent.putExtra("year", year)
//            intent.putExtra("month", month + 1)
//            intent.putExtra("day", dayOfMonth)
//            startActivity(intent)
//        }
    }
//
//    /*****************  hardcoded , remove later ****************/
//    private fun getTotalItems(): Int {
//        return 7
//    }
//
//    private fun getCheckedItems(): Int {
//        return 2
//    }

}
