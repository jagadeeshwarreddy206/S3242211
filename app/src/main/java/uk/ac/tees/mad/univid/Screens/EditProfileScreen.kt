package uk.ac.tees.mad.univid.Screens

import android.Manifest
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.content.PermissionChecker
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import uk.ac.tees.mad.univid.MainViewModel
import uk.ac.tees.mad.univid.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun EditProfileScreen(vm: MainViewModel, navController: NavHostController) {
    val userData = vm.userData
    val name = remember { mutableStateOf(userData.value!!.name) }
    val phoneNumber = remember { mutableStateOf(userData.value!!.phoneNumber) }
    val context = LocalContext.current
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val cameraPermissionGranted = remember { mutableStateOf(false) }
    val isLoading = vm.isLoading

    val cameraLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                imageUri.value?.let { vm.updateProfilePicture(context, it) }
            }
        }

    val permissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) { isGranted ->
            cameraPermissionGranted.value = isGranted
            if (isGranted) {
                val uri = createImageUri(context)
                if (uri != null) {
                    imageUri.value = uri
                    cameraLauncher.launch(uri)
                }
            }
        }

    Scaffold {
        Box {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(200.dp))
                if (userData.value!!.profilePictureUrl.isNotEmpty()) {
                    AsyncImage(
                        model = userData.value!!.profilePictureUrl,
                        contentDescription = "profile picture",
                        modifier = Modifier.size(100.dp).clickable {
                            checkAndRequestPermission(context, permissionLauncher)
                        }
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.man),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .clickable {
                                checkAndRequestPermission(context, permissionLauncher)
                            }
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    label = {
                        Text(text = "Name")
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = phoneNumber.value,
                    onValueChange = { phoneNumber.value = it },
                    label = {
                        Text(text = "Phone Number")
                    }
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = { vm.updateUserData(context,name.value, phoneNumber.value) }) {
                    Text(text = "Save")
                }
            }
            if (isLoading.value){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                CircularProgressIndicator()
            }
            }
        }
    }

}
private fun createImageUri(context: Context): Uri? {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    val file = File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
    return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
}

private fun checkAndRequestPermission(context: Context, permissionLauncher: ActivityResultLauncher<String>) {
    if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PermissionChecker.PERMISSION_GRANTED) {
        permissionLauncher.launch(Manifest.permission.CAMERA)
    } else {
        permissionLauncher.launch(Manifest.permission.CAMERA)
    }
}