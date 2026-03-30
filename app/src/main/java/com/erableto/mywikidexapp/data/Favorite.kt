package com.erableto.mywikidexapp.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorites",
    indices = [Index(value = ["url"], unique = true), Index(value = ["title"], unique = true)]
)
data class Favorite(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val url: String,
    val title: String
)