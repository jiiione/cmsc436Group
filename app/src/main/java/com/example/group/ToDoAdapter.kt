package com.example.group

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList


class ToDoAdapter(private val toDoItems: ArrayList<ToDoItem>, context: Context) :

    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
//    private val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        return ToDoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, pos: Int) {
        val toDoItem = toDoItems[pos]
        holder.itemNameTV.text = toDoItem.title
        holder.descriptionTV.text = toDoItem.description
        holder.checkBox.isChecked = toDoItem.completed

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            toDoItem.completed = isChecked
        }

//        holder.checkBox.setOnClickListener {
//            toDoItem.checked = holder.checkBox.isChecked
//        }
    }


    override fun getItemCount(): Int {
        return toDoItems.size
    }

    fun addItem(item: ToDoItem) {
        toDoItems.add(item)
        notifyItemInserted(toDoItems.size - 1)
    }

    fun removeItem(idx: Int) {
        toDoItems.removeAt(idx)
        notifyItemRemoved(idx)
    }

    inner class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemNameTV: TextView = itemView.findViewById(R.id.itemNameTV)
        val descriptionTV: TextView = itemView.findViewById(R.id.descriptionTV)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkBox)
    }
}
