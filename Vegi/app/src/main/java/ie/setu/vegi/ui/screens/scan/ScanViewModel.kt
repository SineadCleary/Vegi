package ie.setu.vegi.ui.screens.scan

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.vegi.data.api.RetrofitRepository
import ie.setu.vegi.data.models.ProductModel
import ie.setu.vegi.firebase.services.AuthService
import ie.setu.vegi.firebase.services.FirestoreService
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ScanViewModel @Inject
constructor(
    private val retrofitRepository: RetrofitRepository,
    private val firestoreRepository: FirestoreService,
    private val authService: AuthService
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

                    tryInsert(result)
                    Timber.i("Product info : $result")
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

    fun tryInsert(product: ProductModel) = viewModelScope.launch {

        try {

            isLoading.value = true

            if (firestoreRepository.getByBarcode(authService.email!!, product.barcode) == null)
                firestoreRepository.insert(authService.email!!, product)
            else firestoreRepository.update(authService.email!!, product)

        } catch (e: Exception) {

            isErr.value = true
            error.value = e

            Timber.e(e)

        } finally {

            isLoading.value = false
        }
    }
}