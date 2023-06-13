package com.example.moodtracker.ui.moodEvent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MoodEventViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Track your\nMood Event"
    }
    val text: LiveData<String> = _text
}