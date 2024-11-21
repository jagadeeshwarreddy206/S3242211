package uk.ac.tees.mad.univid

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val auth : FirebaseAuth
) : ViewModel() {

    val isLoading = mutableStateOf(false)

    fun signUp(context : Context, email : String, password : String){
        isLoading.value = true
        auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {

        }
    }
}