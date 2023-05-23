package com.example.todoappkpc.model

import androidx.room.*

@Entity
data class Todo(
    @ColumnInfo(name="judul")
    var title:String,
    @ColumnInfo(name="catatan")
    var notes:String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
}
