package com.example.healthyapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthyapp.repository.TestingRepository
import com.example.healthyapp.response.TestResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestingViewModel @Inject constructor(private val repository: TestingRepository) : ViewModel() {
    val testData: MutableState<TestResponse> = mutableStateOf(TestResponse())
    init {
        viewModelScope.launch {
            val result = repository.getTest()
//            Log.d(TAG, "${result.body()}")
            testData.value = result.body()!!
        }
    }
}