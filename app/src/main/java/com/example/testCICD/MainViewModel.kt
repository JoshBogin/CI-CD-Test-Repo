package com.example.testCICD

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    fun getGreeting(greeting: String): String {
        return greeting
    }
}
