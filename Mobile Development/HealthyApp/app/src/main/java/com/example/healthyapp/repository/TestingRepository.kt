package com.example.healthyapp.repository

import com.example.healthyapp.retrofit.TestingService
import javax.inject.Inject

class TestingRepository @Inject constructor(private val testingService: TestingService) {
    suspend fun getTest() = testingService.getTest()
}