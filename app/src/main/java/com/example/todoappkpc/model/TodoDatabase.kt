package com.example.todoappkpc.model

import android.content.Context
import androidx.room.*

class TodoDatabase {
    @Database(entities = arrayOf(Todo::class), version =  1)
    abstract class TodoDatabase:RoomDatabase() {
        abstract fun todoDao(): TodoDao

        companion object {
            @Volatile private var instance: TodoDatabase ?= null
            private val LOCK = Any()

            private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "newtododb").build()

            operator fun invoke(context:Context) {
                if(instance!=null) {
                    synchronized(LOCK) {
                        instance ?: buildDatabase(context).also {
                            instance = it
                        }
                    }
                }
            }
        }
    }
}