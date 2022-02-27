package com.example.takeaway.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.takeaway.repository.FakeAppRepository
import com.example.takeaway.ui.data.repository.BaseRepository
import com.example.takeaway.util.MainDispatcherRule
import com.example.takeaway.util.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
/**
 * Test class for testing viewmodel related functionalities*/
@ExperimentalCoroutinesApi
class AppViewModelTest {
    lateinit var repository: BaseRepository
    lateinit var viewModel: AppViewModel

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val dispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        repository = FakeAppRepository()
        viewModel = AppViewModel(repository)
    }

    @Test
    fun test_get_restaurant_list() {
        val restaurants = viewModel.getRestaurants().getOrAwaitValueTest()
        assertThat(restaurants.size).isEqualTo(FakeAppRepository.ITEM_COUNT)
    }

    @Test
    fun test_sorted_items_based_on_opening_state() {
        val restaurants = viewModel.getRestaurants().getOrAwaitValueTest()
        val currentItem = restaurants[0]
        assertThat(currentItem.status).isEqualTo("open")
    }

    @Test
    fun test_sort_items_based_on_best_match() {
        viewModel.sortOption = SortOption.BestMatch
        val restaurants = viewModel.getRestaurants().getOrAwaitValueTest()
        val maxBestMatchItem = restaurants[0]

        // find restaurant based on bestMatch
        val filteredItem = restaurants.maxByOrNull { it.sortingValues.bestMatch }
        assertThat(maxBestMatchItem).isEqualTo(filteredItem)
    }

    @Test
    fun test_sort_items_based_on_nearest_distance() {
        viewModel.sortOption = SortOption.Distance
        val restaurants = viewModel.getRestaurants().getOrAwaitValueTest()
        val minDistanceItem = restaurants[0]

        // find restaurant based on min distance
        val filteredItem = restaurants.minByOrNull { it.sortingValues.distance }
        assertThat(minDistanceItem).isEqualTo(filteredItem)
    }
}
