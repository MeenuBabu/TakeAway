package com.example.takeaway.ui.data.db.entity

import com.google.gson.annotations.SerializedName

data class RestaurantsModal(
    @SerializedName("restaurants") val restaurants: List<Restaurant>
)
