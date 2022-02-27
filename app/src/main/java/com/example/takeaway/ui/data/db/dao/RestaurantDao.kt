package com.example.takeaway.ui.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.takeaway.ui.data.db.entity.Restaurant

@Dao
interface RestaurantDao {
    /**
     * Insert all items to database.
     * If there any conflicts with objects than it will replace old data.
     *
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<Restaurant>)

    /**
     * Provides all items in database based on sortingValue parameter.
     *
     */
    @Query("SELECT * FROM Restaurant")
    fun getAllRestaurants(): LiveData<List<Restaurant>>

    /**
     * Returns count of saved items.
     *
     */
    @Query("SELECT Count(*) FROM Restaurant")
    fun getCount(): Int
}