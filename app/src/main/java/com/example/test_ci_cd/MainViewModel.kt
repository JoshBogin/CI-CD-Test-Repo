package com.example.test_ci_cd

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    fun getGreeting(greeting: String): String {
        return greeting
    }
}
