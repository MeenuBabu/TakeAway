package com.example.takeaway.ui.main

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.takeaway.R
import org.junit.Rule
import org.junit.Test

/**
 * Unit test class to test the UI functionalities of the application
 * */
class RestaurantActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(RestaurantActivity::class.java)

    @Test
    fun test_launch_activity_and_check_title_displayed() {
        Espresso.onView(withId(R.id.restaurant_toolbar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_launch_activity_and_check_its_title_is_correct() {
        Espresso.onView(withId(R.id.restaurant_toolbar))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withText(R.string.app_name))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_search_button_visibility_then_click_and_check_if_search_layout_displayed() {
        Espresso.onView(withId(R.id.main_toolbar_search_icon))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.main_toolbar_search_icon)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.iv_search_cancel))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_check_search_layout_displayed_and_type_text_and_check_text_visibility() {
        Espresso.onView(withId(R.id.main_toolbar_search_icon)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.atv_search))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        val text = "text"
        Espresso.onView(withId(R.id.atv_search))
            .perform(ViewActions.typeText(text))
        Espresso.onView(withText(text))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_check_if_recyclerView_displayed() {
        Espresso.onView(withId(R.id.rv_restaurants))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_search_by_invalid_text_and_check_recycler_view_hidden_and_NOT_FOUND_view_displayed() {
        Espresso.onView(withId(R.id.main_toolbar_search_icon)).perform(ViewActions.click())

        val invalidText = "INVALID"
        Espresso.onView(withId(R.id.atv_search))
            .perform(ViewActions.typeText(invalidText))

        Espresso.onView(withId(R.id.rv_restaurants))
            .check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
        Espresso.onView(withId(R.id.tv_not_found))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_search_for_restaurant_and_check_if_item_displayed_on_recyclerview() {
        Espresso.onView(withId(R.id.main_toolbar_search_icon)).perform(ViewActions.click())

        val inputText = "Royal"
        val restaurantName = "Royal Thai"
        Espresso.onView(withId(R.id.atv_search))
            .perform(ViewActions.typeText(inputText))
        Espresso.onView(withText(restaurantName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
