package com.example.takeaway.ui.data.repository

import com.example.takeaway.ui.data.db.entity.Restaurant
import kotlinx.coroutines.flow.Flow

interface BaseRepository {
    suspend fun getRestaurants(): Flow<List<Restaurant>>
}