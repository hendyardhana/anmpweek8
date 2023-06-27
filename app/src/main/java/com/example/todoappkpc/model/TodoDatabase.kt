package com.example.todoappkpc.model

import android.content.Context
import androidx.room.*
import com.example.todoappkpc.util.MIGRATION_1_2
import com.example.todoappkpc.util.MIGRATION_2_3
import com.example.todoappkpc.util.MIGRATION_3_4

@Database(entities = [Todo::class], version =  4)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile private var instance: TodoDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, TodoDatabase::class.java, "newtododb")
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4).build()



        operator fun invoke(context:Context) {
            if(instance != null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }
}