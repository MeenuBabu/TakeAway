package com.example.takeaway.ui.main

import android.content.Context
import androidx.room.Room
import com.example.takeaway.ui.Constants
import com.example.takeaway.ui.data.datasource.LocalDataSource
import com.example.takeaway.ui.data.db.RestaurantDatabase
import com.example.takeaway.ui.data.db.dao.RestaurantDao
import com.example.takeaway.ui.data.repository.AppRepository
import com.example.takeaway.ui.data.repository.BaseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides Room database and gives ability to Inject database into other classes.
     *
     */
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RestaurantDatabase::class.java, Constants.DB_NAME).build()

    /**
     * Provides data access object that can be used for database queries.
     */
    @Singleton
    @Provides
    fun provideDAO(db: RestaurantDatabase) = db.dao()

    /**
     * Provides LocalDataSource in order to read JSON data from file and retrieve restaurant list.
     *
     */
    @Singleton
    @Provides
    fun provideLocalDataSource(@ApplicationContext context: Context) = LocalDataSource(context)

    @Singleton
    @Provides
    fun provideBaseRepository(
        dao: RestaurantDao,
        localDataSource: LocalDataSource
    ) = AppRepository(dao, localDataSource) as BaseRepository
}