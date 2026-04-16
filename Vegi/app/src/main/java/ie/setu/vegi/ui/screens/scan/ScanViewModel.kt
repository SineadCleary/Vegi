package ie.setu.vegi.ui.screens.scan

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.vegi.data.api.RetrofitRepository
import ie.setu.vegi.data.models.ProductModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject
constructor(private val repository: RetrofitRepository) : ViewModel(){
    val _product = mutableStateOf<ProductModel?>(null)

    var isErr = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf(Exception())

    fun getProduct(barcode: String) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                val result = repository.get(barcode)

                if (result == null){
                    isErr.value = true
                    error.value = Exception("Product not found")
                    _product.value = null
                }
                else {
                    _product.value = result
                    isErr.value = false
                }
            }
            catch(e: Exception) {
                isErr.value = true
                error.value = e
                Timber.i("RVM Error ${e.message}")
            }
            finally {
                isLoading.value = false
            }
        }
    }

    fun hideError(){
        isErr.value = false
    }
}