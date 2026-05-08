package ie.setu.vegi.ui.screens.add

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
class AddViewModel @Inject
constructor(private val repository: FirestoreService,
            private val authService: AuthService) : ViewModel() {

    fun insert(product: ProductModel)
            = viewModelScope.launch {
        repository.insert(authService.email!!,product)
    }
}
