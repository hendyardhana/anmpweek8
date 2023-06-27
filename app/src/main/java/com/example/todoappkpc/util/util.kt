package com.example.todoappkpc.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todoappkpc.model.TodoDatabase

val DB_NAME = "newtododb"

fun buildDb(context: Context): TodoDatabase = Room.databaseBuilder(context, TodoDatabase::class.java, DB_NAME).addMigrations(
    MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4).build()

val MIGRATION_1_2 = object: Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE 'todo' ADD column priority INTEGER DEFAULT 3 NOT NULL")
        database.execSQL("INSERT INTO todo(title,notes,priority) VALUES('example todo', 'example notes', 3)")
    }
}

val MIGRATION_2_3 = object: Migration(2,3){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE 'todo' ADD column is_done INTEGER DEFAULT 0 NOT NULL")
    }
}

val MIGRATION_3_4 = object: Migration(3,4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE 'todo' ADD column todo_date INTEGER DEFAULT 0 NOT NULL")
    }
}