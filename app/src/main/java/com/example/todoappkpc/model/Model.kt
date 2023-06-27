package com.example.todoappkpc.model

import androidx.room.*

@Entity
data class Todo(
    @ColumnInfo(name="judul")
    var title:String,
    @ColumnInfo(name="catatan")
    var notes:String,
    @ColumnInfo(name="priority")
    var priority:Int,
    @ColumnInfo(name="is_done")
    var isdone:Int,
    var todo_date:Int
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
}