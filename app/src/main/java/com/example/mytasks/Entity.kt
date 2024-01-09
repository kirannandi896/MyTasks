package com.example.mytasks

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyTask")
data class Entity (
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var title:String,
    var priority:String,
    var ftime:String,
    var ttime:String,
    var fdate:String,
    var tdate:String,
    var done: Boolean
)
