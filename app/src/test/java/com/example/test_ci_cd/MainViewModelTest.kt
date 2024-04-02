package com.example.test_ci_cd

import org.junit.Assert.assertEquals
import org.junit.Test

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
