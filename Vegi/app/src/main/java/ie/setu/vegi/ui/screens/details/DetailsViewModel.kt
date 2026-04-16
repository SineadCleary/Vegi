package ie.setu.vegi.ui.screens.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.vegi.data.models.ProductModel
import ie.setu.vegi.data.repository.RoomRepository
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class DetailsViewModel @Inject
constructor(private val repository: RoomRepository,
            savedStateHandle: SavedStateHandle
) : ViewModel() {

    var product = mutableStateOf(ProductModel())
    val barcode: String = checkNotNull(savedStateHandle["barcode"])

    init {
        viewModelScope.launch {
            repository.get(barcode).collect { objProduct ->
                product.value = objProduct
            }
        }
    }

    fun updateProduct(product: ProductModel) {
        viewModelScope.launch { repository.update(product) }
    }
}
