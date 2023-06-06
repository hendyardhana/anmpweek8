package com.example.todoappkpc.model

import androidx.room.*

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo:Todo) //Bisa banyak object

    @Query("SELECT * FROM todo ORDER BY priority DESC")
    fun selectAllTodo_(): List<Todo>

    @Query("SELECT * FROM todo WHERE is_done=0 ORDER BY priority DESC")
    fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid= :id")
    fun selectTodo(id:Int): Todo

    @Query("UPDATE 'todo' SET judul=:title, catatan=:notes, priority=:priority WHERE uuid=:uuid")
    fun update(title:String, notes:String, priority:Int, uuid:Int)

    @Query("UPDATE 'todo' SET is_done=:isdone WHERE uuid=:uuid")
    fun updateIsDone(isdone:Int, uuid:Int)

    @Delete
    fun deleteTodo(todo:Todo)
}