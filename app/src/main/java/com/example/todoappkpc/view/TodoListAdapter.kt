package com.example.todoappkpc.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappkpc.R
import com.example.todoappkpc.databinding.TodoItemLayoutBinding
import com.example.todoappkpc.model.Todo

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick:(Todo) -> Unit) : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(), TodoItemInterface{
    class TodoViewHolder(var view: TodoItemLayoutBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = TodoItemLayoutBinding.inflate(inflater, parent, false)

        return TodoViewHolder(view)

    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
//        val checktask = holder.view.findViewById<CheckBox>(R.id.checkTask)
//        checktask.text = todoList[position].title
//        checktask.isChecked = false
//
//        checktask.setOnCheckedChangeListener { compoundButton, isChecked ->
//            if(isChecked){
//                adapterOnClick(todoList[position])
//            }
//        }
//
//        val imgEdit = holder.view.findViewById<ImageView>(R.id.imgEdit)
//        imgEdit.setOnClickListener {
//            val action = TodoListFragmentDirections.actionEditFragment(todoList[position].uuid)
//            Navigation.findNavController(it).navigate(action)
//        }

        holder.view.todo = todoList[position]
        holder.view.listener = this
        holder.view.checkTask.isChecked = false
        holder.view.editlistener = this
    }

    fun updateTodoList(newTodoList: ArrayList<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCheckedChanged(cb: CompoundButton, isChecked: Boolean, todo: Todo) {
        if(isChecked){
            adapterOnClick(todo)
        }
    }

    override fun onTodoEditClick(view: View) {
        val action = TodoListFragmentDirections.actionEditFragment(view.tag.toString().toInt())
            Navigation.findNavController(view).navigate(action)
    }

}