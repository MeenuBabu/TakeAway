package com.example.takeaway.ui.data.db

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.takeaway.ui.data.db.dao.RestaurantDao
import com.example.takeaway.ui.data.db.entity.Restaurant
import com.example.takeaway.ui.data.db.entity.SortingValues
import com.example.takeaway.util.getOrAwaitValue
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Unit test class to test database related test cases of the application
 * */

class RestaurantDatabaseTest {
    private lateinit var db: RestaurantDatabase
    private lateinit var dao: RestaurantDao

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, RestaurantDatabase::class.java).build()
        dao = db.dao()

    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun test_insert_restaurant_item_into_db() = runBlocking {
        val sortingValues = SortingValues(1, 1.1, 1.1, 1, 1.1, 1, 1, 1)
        val restaurant = Restaurant("Test Restaurant1", "open", sortingValues)
        dao.insert(listOf(restaurant))

        val itemCount = dao.getCount()
        assertEquals(itemCount, 1)
    }

    @Test
    fun test_insert_3_items_and_check_count() = runBlocking {
        val sortingValues = SortingValues(1, 1.1, 1.1, 1, 1.1, 1, 1, 1)
        val restaurant1 = Restaurant("Test Restaurant1", "open", sortingValues)
        val restaurant2 = Restaurant("Test Restaurant2", "closed", sortingValues)
        val restaurant3 = Restaurant("Test Restaurant3", "order ahead", sortingValues)
        dao.insert(listOf(restaurant1, restaurant2, restaurant3))

        val itemsCount = dao.getCount()
        assertEquals(itemsCount, 3)
    }

    @Test
    fun test_insert_and_get_new_item() = runBlocking {
        val sortingValues = SortingValues(1, 1.1, 1.1, 1, 1.1, 1, 1, 1)
        val restaurant = Restaurant("Test Restaurant", "open", sortingValues, 1)
        dao.insert(listOf(restaurant))

        val items: List<Restaurant> = dao.getAllRestaurants().getOrAwaitValue()
        assertEquals(items.contains(restaurant), true)
    }
}