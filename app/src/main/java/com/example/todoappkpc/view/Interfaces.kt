package com.example.todoappkpc.view

import android.view.View
import android.widget.CompoundButton
import com.example.todoappkpc.model.Todo

interface TodoItemInterface {
    fun onCheckedChanged(cb: CompoundButton, isChecked:Boolean, todo:Todo)
    fun onTodoEditClick(view:View)
}

interface FragmentEditTodoInterface {
    fun onRadioClick(view: View, todo: Todo)
    fun onTodoSaveClick(view: View, todo: Todo)
}