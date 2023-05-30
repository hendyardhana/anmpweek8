package com.example.todoappkpc.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import com.example.todoappkpc.model.Todo
import com.example.todoappkpc.model.TodoDatabase
import com.example.todoappkpc.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    private val job = Job()

    fun addTodo(todo: Todo) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().insertAll(todo)
        }
    }
}