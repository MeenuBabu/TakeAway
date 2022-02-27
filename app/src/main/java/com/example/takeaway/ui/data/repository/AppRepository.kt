package com.example.takeaway.ui.data.repository

import androidx.lifecycle.asFlow
import com.example.takeaway.ui.data.datasource.LocalDataSource
import com.example.takeaway.ui.data.db.dao.RestaurantDao
import com.example.takeaway.ui.data.db.entity.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Class responsible for collecting all data and supplying to viewmodel
 * */

class AppRepository @Inject constructor(
    private val dao: RestaurantDao,
    private val localDataSource: LocalDataSource
) : BaseRepository {
    override suspend fun getRestaurants(): Flow<List<Restaurant>> {
        return withContext(Dispatchers.IO) {
            val count = dao.getCount()
            if (count == 0) {
                dao.insert(localDataSource.getRestaurantList())
            }
            dao.getAllRestaurants().asFlow()
        }
    }

}