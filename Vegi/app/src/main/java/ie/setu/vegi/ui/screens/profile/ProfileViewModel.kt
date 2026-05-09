package ie.setu.vegi.ui.screens.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import ie.setu.vegi.firebase.services.AuthService
import ie.setu.vegi.firebase.services.FirestoreService
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authService: AuthService,
    private val auth: FirebaseAuth,
    private val firestoreRepository: FirestoreService,
) : ViewModel() {

    val displayName get() = auth.currentUser?.displayName.toString()
    val email get() = auth.currentUser?.email.toString()
    val photoUri get() = authService.customPhotoUri

    fun signOut() {
        viewModelScope.launch { authService.signOut() }
    }

    fun updatePhotoUri(uri: Uri) {
        viewModelScope.launch { authService.updatePhoto(uri) }
    }

    fun deleteHistory(email: String) {
        viewModelScope.launch { firestoreRepository.deleteUserHistory(email) }
    }
}