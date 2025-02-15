package com.example.ramy

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

data class DetectionResponse(
    val products_detected: Map<String, Int>
)

interface ApiService {
    @Multipart
    @POST("predict")
    suspend fun detectProducts(
        @Part image: MultipartBody.Part
    ): Response<DetectionResponse>
}