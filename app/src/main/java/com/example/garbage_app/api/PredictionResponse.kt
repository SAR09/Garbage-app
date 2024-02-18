package com.example.garbage_app.api

import com.google.gson.annotations.SerializedName

data class PredictionResponse(
    @SerializedName("prediction")
    val prediction: String
)
