package ie.setu.vegi.ui.screens.scan

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.vegi.data.api.RetrofitRepository
import ie.setu.vegi.data.models.ProductModel
import ie.setu.vegi.data.repository.RoomRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject
constructor(
    private val retrofitRepository: RetrofitRepository,
    private val roomRepository: RoomRepository
) : ViewModel(){
    val _product = mutableStateOf<ProductModel?>(null)

    var isErr = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf(Exception())

    fun getProduct(barcode: String) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                val result = retrofitRepository.get(barcode)

                if (result == null){
                    isErr.value = true
                    error.value = Exception("Product not found")
                    _product.value = null
                }
                else {
                    _product.value = result
                    isErr.value = false

                    insert(result)
                    Timber.i("Product info : $_product")
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

    fun insert(products: ProductModel)
            = viewModelScope.launch {
        roomRepository.insert(products)
    }
}