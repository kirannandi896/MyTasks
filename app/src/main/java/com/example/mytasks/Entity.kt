package com.example.mytasks

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tasks")
data class Entity (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title:String?,
    var priority:String?,
    var done: Boolean?
)
