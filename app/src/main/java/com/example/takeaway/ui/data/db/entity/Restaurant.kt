package com.example.takeaway.ui.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Restaurant(
    @SerializedName("name") val name: String,
    @SerializedName("status") val status: String,
    @SerializedName("sortingValues") val sortingValues: SortingValues,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)