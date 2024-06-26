package com.example.group

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
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
    private lateinit var backBT: Button

    private lateinit var progressBar: ProgressBar
    private lateinit var percentageTV: TextView

    private val toDoItems = ArrayList<ToDoItem>()
    private lateinit var adapter: ToDoAdapter

    private var totalItems : Int = 0
    private var checkedItems : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todolist)

        dateTV = findViewById(R.id.dateTV)
        recyclerView = findViewById(R.id.recyclerView)
        addBT = findViewById(R.id.addBT)
        backBT = findViewById(R.id.backBT)
        progressBar = findViewById(R.id.progressBar)
        percentageTV = findViewById(R.id.percentageTV)


        val year = intent.getIntExtra("year", 0)
        val month = intent.getIntExtra("month", 0)
        val day = intent.getIntExtra("day", 0)
//        Log.d("Calendar ", "Year: $year, Month: $month, Day: $day")

        val cal = Calendar.getInstance()
        cal.set(year, month, day)

        val dayMonthYear = SimpleDateFormat("dd MMMM yyyy", Locale.US)
        val dateString = dayMonthYear.format(cal.time)
        dateTV.text = dateString

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ToDoAdapter(toDoItems, this)

        recyclerView.adapter = adapter

        /*********** need to fix *************************************/
        var pref = getSharedPreferences("totalItems", Context.MODE_PRIVATE)
        var editor = pref.edit()
        editor.putInt("totalItems", toDoItems.size)
        editor.commit()

        /*********** need to fix *************************************/
        var prefChecked = getSharedPreferences("checkedItems", Context.MODE_PRIVATE)
        checkedItems = prefChecked.getInt("checkedItems", ToDoAdapter.checkedItems)

        // totalItems = getTotalItems()
//        ToDoAdapter.checkedItems = getCheckedItems()
        var percentage = ((ToDoAdapter.checkedItems.toFloat() / totalItems) * 100).toInt()
        percentageTV.text = percentage.toString() + "%"
        progressBar.progress = percentage

        Log.w("ToDoList", "total = "+ totalItems)
        Log.w("ToDoList", "checked = "+ getCheckedItems())
        Log.w("ToDoList", "percentage = "+ percentage)

        addBT.setOnClickListener {
            showInputDialog()
        }

        backBT.setOnClickListener {
            /***** need to change later *************************************/
            val intent = Intent(this, MainActivity::class.java)
            startActivity( intent )
        }
    }

//    fun addItem(item: ToDoItem) {
//        adapter.addItem(item)
//    }

//    fun removeItem(pos: Int) {
//        adapter.removeItem(pos)
//    }

    private fun showInputDialog() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.todo_input, null)

        val nameInputET = dialogLayout.findViewById<EditText>(R.id.nameInputET)
        val descriptionET = dialogLayout.findViewById<EditText>(R.id.descriptionET)

        builder.setView(dialogLayout)
            .setPositiveButton("Add") { _, _ ->
                val itemName = nameInputET.text.toString()
                val description = descriptionET.text.toString()

                if (itemName.isNotEmpty()) {
                    addItem(itemName, description)
                     totalItems++

                } else {
                }
            }
            .setNegativeButton("Cancel", null)

        val dialog = builder.create()
        dialog.show()
    }

    private fun addItem(itemName: String, description: String) {
        val item = ToDoItem(itemName, description)
        toDoItems.add(item)
        recyclerView.adapter?.notifyDataSetChanged()

//        ToDoAdapter.notifyItemInserted(toDoItems.size - 1)
    }

    private fun getTotalItems(): Int {
        return totalItems
//        return toDoItems.size
    }

    private fun getCheckedItems(): Int {
        return ToDoAdapter.checkedItems
    }

}

