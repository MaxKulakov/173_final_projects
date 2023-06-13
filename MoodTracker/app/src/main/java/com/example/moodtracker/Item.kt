package com.example.moodtracker

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "items")
data class Item (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "mood")
    var mood: Int,
    @ColumnInfo(name = "describe")
    var describe: String,
    @ColumnInfo(name = "date")
    var date: String

)