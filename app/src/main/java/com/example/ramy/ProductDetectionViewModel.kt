package com.example.ramy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.graphics.Bitmap
import android.content.Context
import java.io.File
import java.io.FileOutputStream
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

data class DetectionUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val detectionResults: Map<String, Int>? = null,
    val currentImage: Bitmap? = null
)

sealed class DetectionEvent {
    data class ImageCaptured(val bitmap: Bitmap, val context: Context) : DetectionEvent()
    object Reset : DetectionEvent()
}

class ProductDetectionViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(DetectionUiState())
    val uiState: StateFlow<DetectionUiState> = _uiState.asStateFlow()

    fun handleEvent(event: DetectionEvent) {
        when (event) {
            is DetectionEvent.ImageCaptured -> processImage(event.bitmap, event.context)
            DetectionEvent.Reset -> resetState()
        }
    }

    private fun processImage(bitmap: Bitmap, context: Context) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(
                    isLoading = true,
                    currentImage = bitmap,
                    error = null
                )

                val results = sendImageToBackend(bitmap, context)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    detectionResults = results
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    private suspend fun sendImageToBackend(bitmap: Bitmap, context: Context): Map<String, Int> {
        val file = convertBitmapToFile(bitmap, context)
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

        val response = NetworkModule.apiService.detectProducts(body)
        if (response.isSuccessful) {
            return response.body()?.products_detected ?: emptyMap()
        } else {
            throw Exception("Failed to process image: ${response.message()}")
        }
    }

    private fun convertBitmapToFile(bitmap: Bitmap, context: Context): File {
        val file = File(context.cacheDir, "temp_image_${System.currentTimeMillis()}.jpg")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
        }
        return file
    }

    private fun resetState() {
        _uiState.value = DetectionUiState()
    }
}