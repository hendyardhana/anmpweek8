package com.example.todoappkpc.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappkpc.R
import com.example.todoappkpc.model.Todo

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick:(Todo) -> Unit) : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(){
    class TodoViewHolder(var view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)

        return TodoViewHolder(view)

    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var checktask = holder.view.findViewById<CheckBox>(R.id.checkTask)
        checktask.text = todoList[position].title
        checktask.isChecked = false

        checktask.setOnCheckedChangeListener { compoundButton, b ->
            adapterOnClick(todoList[position])
        }
    }

    fun updateTodoList(newTodoList: ArrayList<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

}