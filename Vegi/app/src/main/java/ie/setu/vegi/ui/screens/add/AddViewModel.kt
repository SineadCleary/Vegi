package ie.setu.vegi.ui.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.vegi.data.models.ProductModel
import ie.setu.vegi.data.repository.RoomRepository
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AddViewModel @Inject
constructor(private val repository: RoomRepository) : ViewModel() {

    fun insert(products: ProductModel)
            = viewModelScope.launch {
        repository.insert(products)
    }
}
