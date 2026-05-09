package ie.setu.vegi.ui.screens.profile

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ie.setu.vegi.R
import ie.setu.vegi.ui.components.general.HeadingTextComponent
import ie.setu.vegi.ui.components.general.ShowPhotoPicker
import ie.setu.vegi.ui.components.history.showDeleteAlert
import ie.setu.vegi.ui.screens.login.LoginViewModel
import ie.setu.vegi.ui.screens.register.RegisterViewModel

@Composable
fun ProfileScreen(
    onSignOut: () -> Unit = {},
    profileViewModel: ProfileViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel(),
    registerViewModel: RegisterViewModel = hiltViewModel(),
) {
    var photoUri: Uri? by remember { mutableStateOf(profileViewModel.photoUri) }
    var showDeleteConfirmDialog by remember { mutableStateOf(false) }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        HeadingTextComponent(value = stringResource(id = R.string.account_settings))
        Spacer(modifier = Modifier.height(6.dp))

        if(photoUri.toString().isNotEmpty())
            ProfileContent(
                photoUri = photoUri,
                displayName = profileViewModel.displayName,
                email = profileViewModel.email
            )
        ShowPhotoPicker(
            onPhotoUriChanged = {
                photoUri = it
                profileViewModel.updatePhotoUri(photoUri!!)
            }
        )
        Row {
            Button(
                onClick = {
                    profileViewModel.signOut()
                    onSignOut()
                    loginViewModel.resetLoginFlow()
                    registerViewModel.resetRegisterFlow()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
            ) {
                Text(text = "Logout")
            }
            Spacer(modifier = Modifier.width(6.dp))
            Button(
                onClick = {
                    showDeleteConfirmDialog = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
            ) {
                Text(text = "Clear history")
            }
            if (showDeleteConfirmDialog) {
                ShowClearAlert(
                    onDismiss = { showDeleteConfirmDialog = false },
                    onDelete = {
                        profileViewModel.deleteHistory(profileViewModel.email)
                        showDeleteConfirmDialog = false
                    }
                )
            }
        }
    }}

@Composable
fun ShowClearAlert(
    onDismiss: () -> Unit,
    onDelete: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss ,
        title = { Text(stringResource(id = R.string.confirm_clear)) },
        text = { Text(stringResource(id = R.string.confirm_clear_message)) },
        confirmButton = {
            Button(
                onClick = { onDelete() }
            ) { Text("Clear") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Cancel") }
        }
    )
}
