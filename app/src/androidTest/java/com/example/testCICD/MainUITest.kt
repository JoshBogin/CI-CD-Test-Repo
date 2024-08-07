package com.example.testCICD

import android.content.Context
import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainUITest {
    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()
    lateinit var appContext: Context
    var greeting = ""
    @Before
    fun setUp() {
        // Context of the app under test.
        appContext = InstrumentationRegistry.getInstrumentation().targetContext

        greeting = rule.activity.getString(R.string.hello_world)
        rule.activity.setContent { Greeting(greeting) }
    }

    @Test
    fun useAppContext() {
        assertEquals("com.joshbogin.testCICD", appContext.packageName)
    }

    @Test
    fun testGreeting() {
        // check if the greeting is displayed
        rule.onNodeWithText(greeting).assertExists()
    }
}
