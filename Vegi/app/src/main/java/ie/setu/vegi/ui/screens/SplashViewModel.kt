package ie.setu.vegi.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Code based on code found at: https://medium.com/@paritasampa95/implementing-splashscreen-using-splashscreen-api-in-android-jetpack-compose-2397fc5ed2cb
class SplashViewModel : ViewModel() {
    private val mutableStateFlow = MutableStateFlow(true)
    val isLoading = mutableStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            delay(300)
            mutableStateFlow.value = false
        }
    }
}