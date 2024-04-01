package com.example.test_ci_cd

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainViewModelTest {
    private lateinit var viewModel: MainViewModel

    private fun initViewModel() {
        viewModel = MainViewModel()
    }

    @Test
    fun `GetGreeting returns hello world text`() {
        initViewModel()

        assertEquals("Hello, World!", viewModel.getGreeting("Hello, World!"))
    }
}