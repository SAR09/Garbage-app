package com.example.garbage_app

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.garbage_app.api.ApiService
import com.example.garbage_app.api.PredictionResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream


class PredictViewModel(
    private val context: Context
) : ViewModel() {

    private val apiService: ApiService

    private val _predictionResult = MutableLiveData<String>()
    val predictionResult: LiveData<String> get() = _predictionResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _resultText = MutableLiveData<String>()
    val resultText: LiveData<String> get() = _resultText

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://garbageapp-api-e29add74242a.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    fun processImage(bitmap: Bitmap) {

        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val imageByteArray = byteArrayOutputStream.toByteArray()

        val requestBody = RequestBody.create("image/jpeg".toMediaTypeOrNull(), imageByteArray)
        val imgPart = MultipartBody.Part.createFormData("image", "image.jpg", requestBody)
        // Melakukan panggilan API
        val call = apiService.predictImage(imgPart)
        call.enqueue(object : Callback<PredictionResponse> {
            override fun onResponse(
                call: Call<PredictionResponse>,
                response: Response<PredictionResponse>
            ) {
                if (response.isSuccessful) {
                    val predictionResponse = response.body()
                    if (predictionResponse != null) {
                        val prediction = predictionResponse.prediction
                        val resultDescription = getResultDescription(prediction)
                        showToast(resultDescription)
                        Log.d(TAG, "Hasil prediksi: $resultDescription")
                        _resultText.value = "Hasil Prediksi: $resultDescription"
                        // Log respons JSON
                        Log.d(TAG, "Respons JSON: ${response.body()}")
                    } else {
                        _errorMessage.value = "Error: Gagal memparsing respons"
                        Log.e(TAG, "Error: Gagal memparsing respons")
                    }
                } else {
                    _errorMessage.value = "Server Off"
                    Log.e(TAG, "Server Off")
                }
            }

            override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                _errorMessage.value = "Request failed: ${t.message}"
                Log.e(TAG, "Request failed", t)
            }
        })
    }

    fun getResultDescription(prediction: String): String {
        return prediction
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }


    companion object {
        const val TAG = "PredictViewModel"
    }
}