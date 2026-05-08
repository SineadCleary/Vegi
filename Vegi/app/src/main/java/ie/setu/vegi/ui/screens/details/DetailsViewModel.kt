package ie.setu.vegi.ui.screens.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.vegi.data.models.ProductModel
import ie.setu.vegi.data.repository.RoomRepository
import ie.setu.vegi.firebase.services.AuthService
import ie.setu.vegi.firebase.services.FirestoreService
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DetailsViewModel @Inject
constructor(private val repository: FirestoreService,
            private val authService: AuthService,
            savedStateHandle: SavedStateHandle
) : ViewModel() {

    var product = mutableStateOf(ProductModel())
    val barcode: String = checkNotNull(savedStateHandle["barcode"])

//    init {
//        viewModelScope.launch {
//            repository.get(authService.email!!,barcode).collect { objProduct ->
//                product.value = objProduct
//            }
//        }
//    }

    fun updateProduct(product: ProductModel) {
        viewModelScope.launch { repository.update(authService.email!!, product) }
    }
}
