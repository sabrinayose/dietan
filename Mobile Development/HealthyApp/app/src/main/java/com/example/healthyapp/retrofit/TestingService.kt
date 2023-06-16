package com.example.healthyapp.retrofit

import com.example.healthyapp.response.TestResponse
import com.example.healthyapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET

interface TestingService {
    @GET(Constants.RANDOM_URL)
    suspend fun getTest(): Response<TestResponse>
}