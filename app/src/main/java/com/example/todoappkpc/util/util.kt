package com.example.todoappkpc.util

import android.content.Context
import androidx.room.Room
import com.example.todoappkpc.model.TodoDatabase

val DB_NAME = "newtododb"

fun buildDb(context: Context): TodoDatabase = Room.databaseBuilder(context, TodoDatabase::class.java, DB_NAME).build()