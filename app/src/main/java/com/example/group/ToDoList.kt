package com.example.group

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.*

class ToDoList : AppCompatActivity() {

    private lateinit var dateTV: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var addBT: FloatingActionButton
    private val toDoItems = ArrayList<ToDoItem>()
    private lateinit var adapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todolist)

        dateTV = findViewById(R.id.dateTV)
        recyclerView = findViewById(R.id.recyclerView)
        addBT = findViewById(R.id.addBT)

        val year = intent.getIntExtra("year", 0)
        val month = intent.getIntExtra("month", 0)
        val day = intent.getIntExtra("day", 0)
        val dayMonthYear = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        val dateString = dayMonthYear.format(Date(year, month, day))
        dateTV.text = dateString

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ToDoAdapter(toDoItems, this)

        recyclerView.adapter = adapter


        val newItem = ToDoItem( "New Item", dateString, false)
        toDoItems.add(newItem)

        addItem(newItem)
        recyclerView.adapter?.notifyDataSetChanged()


        addBT.setOnClickListener {
            val item = EditText(this).apply {
                hint = "Enter "
            }.text.toString()

            if (item.isNotBlank()) {
                val newItem = ToDoItem( item, dateString, false)

                toDoItems.add(newItem)
                addItem(newItem)

                recyclerView.adapter?.notifyDataSetChanged()
            } else {
            }
        }

    }

    fun addItem(item: ToDoItem) {
        adapter.addItem(item)
    }

    fun removeItem(pos: Int) {
        adapter.removeItem(pos)
    }

}

