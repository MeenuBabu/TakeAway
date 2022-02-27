package com.example.takeaway.ui.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.takeaway.ui.Constants
import com.example.takeaway.ui.data.db.dao.RestaurantDao
import com.example.takeaway.ui.data.db.entity.Restaurant
import com.example.takeaway.ui.data.db.entity.SortingValues
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Room database for storing restaurant data
 * */

@Database(entities = [Restaurant::class], version = Constants.DB_VERSION, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract fun dao(): RestaurantDao
}

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun objectToString(sortingValues: SortingValues): String = gson.toJson(sortingValues)

    @TypeConverter
    fun stringToObject(jsonData: String): SortingValues {
        val itemType = object : TypeToken<SortingValues>() {}.type
        return gson.fromJson(jsonData, itemType)
    }

}