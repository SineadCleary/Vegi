package ie.setu.vegi.ui.screens.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.vegi.data.models.ProductModel
import ie.setu.vegi.data.repository.RoomRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HistoryViewModel @Inject
constructor(private val repository: RoomRepository) : ViewModel() {
    private val _products
            = MutableStateFlow<List<ProductModel>>(emptyList())
    val uiProducts: StateFlow<List<ProductModel>>
            = _products.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAll().collect { listOfProducts ->
                _products.value = listOfProducts
            }
        }
    }

    fun deleteProduct(product: ProductModel) {
        viewModelScope.launch {
            repository.delete(product)
        }
    }

}
