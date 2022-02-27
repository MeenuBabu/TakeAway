package com.example.takeaway.ui.main

import android.content.Context
import androidx.room.Room
import com.example.takeaway.ui.data.db.RestaurantDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestModule {

    @Provides
    @Named("test_db")
    fun provideSampleDatabase(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, RestaurantDatabase::class.java)
            .build()
}