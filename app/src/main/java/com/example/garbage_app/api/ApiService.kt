package com.example.garbage_app.api


import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @POST("predict")
    @Multipart
    fun predictImage(
        @Part imgFile: MultipartBody.Part,
    ): Call<PredictionResponse>
}

