package com.example.takeaway.ui.data.datasource

import android.content.Context
import com.example.takeaway.ui.Constants
import com.example.takeaway.ui.data.db.entity.Restaurant
import com.example.takeaway.ui.data.db.entity.RestaurantsModal
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LocalDataSource(private val context: Context) {
    /**
     * Reads JSON file and returns list of Restaurant based of file content.
     *
     */
    fun getRestaurantList(): List<Restaurant> {
        val content = readFile()
        val modalClass: RestaurantsModal =
            Gson().fromJson(content, object : TypeToken<RestaurantsModal>() {}.type)
        return modalClass.restaurants
    }

    /**
     * Reads JSON file from assets folder and returns file content.
     *
     */
    private fun readFile(): String = context.assets
        .open(Constants.RESTAURANT_JSON_FILE)
        .bufferedReader()
        .use { it.readText() }
}
