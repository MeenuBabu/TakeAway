package com.example.takeaway

import androidx.test.platform.app.InstrumentationRegistry
import com.example.takeaway.ui.data.db.RestaurantDatabaseTest
import com.example.takeaway.ui.main.RestaurantActivityTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Test suite class created to run all instrumentation test cases with single click.
 * */

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    RestaurantActivityTest::class,
    RestaurantDatabaseTest::class
)
class UITestSuite {

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.takeaway", appContext.packageName)
    }

}