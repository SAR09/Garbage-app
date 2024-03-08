package com.example.garbage_app
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.garbage_app.api.ApiService
import com.example.garbage_app.api.PredictionResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.ContentValues.TAG
import android.widget.LinearLayout
import com.google.firebase.auth.FirebaseAuth


class PredicitionActivity : AppCompatActivity() {

    private lateinit var imgPreview: ImageView
    private lateinit var btnGallery: Button
    private lateinit var btnCamera: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var tvResultText: TextView
    private lateinit var predictButton: Button
    private lateinit var firebaseAuth: FirebaseAuth

    //Navigation bar
    private lateinit var homeLayout: LinearLayout
    private lateinit var predictionLayout: LinearLayout
    private lateinit var logout: LinearLayout

    private val PICK_IMAGE_REQUEST = 1
    private val CAMERA_PERMISSION_REQUEST_CODE = 101
    private val CAMERA_REQUEST = 102

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://garbageapp-api-e29add74242a.herokuapp.com/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val apiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let { imageUri ->
            imgPreview.setImageURI(imageUri)
        }
    }

    private val _predictionResult = MutableLiveData<String>()
    val predictionResult: LiveData<String> get() = _predictionResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _resultText = MutableLiveData<String>()
    val resultText: LiveData<String> get() = _resultText

    private val takePicture = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data") as Bitmap
            imgPreview.setImageBitmap(imageBitmap)
        }else{
            // Image capture failed or was cancelled, handle it accordingly
            Toast.makeText(this, "Pengambilan gambar gagal atau dibatalkan", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_predicition)

        firebaseAuth = FirebaseAuth.getInstance()

        imgPreview = findViewById(R.id.img_preview)
        btnGallery = findViewById(R.id.btn_gallery)
        btnCamera = findViewById(R.id.btn_camera)
        progressBar = findViewById(R.id.progress_bar)
        tvResultText = findViewById(R.id.tv_resultText)
        predictButton = findViewById(R.id.predict)

        btnGallery.setOnClickListener { openGallery() }
        btnCamera.setOnClickListener { openCamera() }
        predictButton.setOnClickListener { predictImage() }

        predictionLayout = findViewById(R.id.prediction)
        homeLayout = findViewById(R.id.home)
        logout = findViewById(R.id.logout)


        homeLayout.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        predictionLayout.setOnClickListener {
            startActivity(Intent(this, PredicitionActivity::class.java))
        }

        logout.setOnClickListener {
            firebaseAuth.signOut()
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
            goToSignInActivity()
        }
    }

    private fun goToSignInActivity() {
        val intent = Intent(this, SignInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }


    private fun openGallery() {
        pickImage.launch("image/*")
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePicture.launch(intent)
    }

    private fun predictImage() {
        // Get the bitmap from image view
        val bitmap = imgPreview.drawable.toBitmap()

        // Convert bitmap to byte array
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val byteArray = stream.toByteArray()

        // Create RequestBody
        val requestBody = byteArray.inputStream().use { it.readBytes().toRequestBody("image/*".toMediaTypeOrNull()) }
        val body = MultipartBody.Part.createFormData("file", "image.jpg", requestBody)

        progressBar.visibility = ProgressBar.VISIBLE

        // Make the request
        apiService.predictImage(body).enqueue(object : Callback<PredictionResponse> {
            override fun onResponse(call: Call<PredictionResponse>, response: Response<PredictionResponse>) {
                progressBar.visibility = ProgressBar.GONE

                if (response.isSuccessful) {
                    val predictionResponse = response.body()
                    if (predictionResponse != null) {
                        val prediction = predictionResponse.prediction
                        val resultDescription = getResultDescription(prediction)
                        showToast(resultDescription)
                        Log.d(TAG, "Hasil prediksi: $resultDescription")
                        _resultText.value = "Hasil Prediksi: $resultDescription"
                        // Set result text to TextView
                        tvResultText.text = resultDescription
                        // Log respons JSON
                        Log.d(TAG, "Respons JSON: ${response.body()}")
                    } else {
                        _errorMessage.value = "Error: Gagal memparsing respons"
                        Log.e(TAG, "Error: Gagal memparsing respons")
                    }
                } else {
                    Log.e(TAG, "Gagal melakukan prediksi: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PredictionResponse>, t: Throwable) {
                progressBar.visibility = ProgressBar.GONE
                Log.e(TAG, "Error: ${t.message}")
            }
        })
    }

    private fun hasCameraPermission(): Boolean {
        val permission = Manifest.permission.CAMERA
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            openCamera()
        } else {
            Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    fun getResultDescription(prediction: String): String {
        return prediction
    }

    private fun showToast(message: String) {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
    }

    private fun handleResponse(uploadResponse: PredictionResponse?) {
        uploadResponse?.let {
            // Update UI
            tvResultText.text = it.prediction
            progressBar.visibility = ProgressBar.GONE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PICK_IMAGE_REQUEST -> {
                    val imageUri = data?.data
                    imgPreview.setImageURI(imageUri)
                }
                CAMERA_REQUEST -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    imgPreview.setImageBitmap(imageBitmap)
                }
            }
        }
    }
}


