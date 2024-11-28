package uk.ac.tees.mad.univid

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import uk.ac.tees.mad.univid.Models.Api.ApiResponse
import uk.ac.tees.mad.univid.Models.Api.Data
import uk.ac.tees.mad.univid.Models.User
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val auth : FirebaseAuth,
    private val firestore : FirebaseFirestore,
    private val repository: AppRepository
) : ViewModel() {

    val isLoading = mutableStateOf(false)
    val isSignedIn = mutableStateOf(false)

    private val _response = MutableStateFlow<ApiResponse?>(null)
    val response: StateFlow<ApiResponse?> = _response

    init {
        if (auth.currentUser != null){
            isSignedIn.value = true
            getUserData()
        }
        fetchData()
    }

    private fun getUserData() {
    }

    fun signUp(context : Context,name : String, phoneNumber : String, email : String, password : String){
        isLoading.value = true
        auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
            firestore.collection("users").document(it.user!!.uid).set(
                User(
                    id = it.user!!.uid,
                    email = email,
                    password = password,
                    name = name,
                    phoneNumber = phoneNumber
                )
            ).addOnSuccessListener {
                isLoading.value = false
                Toast.makeText(context,"Sign Up Successful", Toast.LENGTH_SHORT).show()
                isSignedIn.value = true
                getUserData()
            }.addOnFailureListener {
                isLoading.value = false
                Log.d("Sign Up", "signUp: ${it.message}")
                Toast.makeText(context,it.message, Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            isLoading.value = false
            Log.d("Sign Up", "signUp: ${it.message}")
            Toast.makeText(context,it.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun signIn(context: Context, email : String, password: String){
        isLoading.value = true
        auth.signInWithEmailAndPassword(
            email,password
        ).addOnSuccessListener {
            isLoading.value = false
            isSignedIn.value = true
            getUserData()
            Toast.makeText(context, "Sign In Successful", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            isLoading.value = false
            Toast.makeText(context,it.message, Toast.LENGTH_SHORT).show()
            Log.d("Sign In", "signIn: ${it.message}")

        }
    }

    fun fetchData(){
        viewModelScope.launch {
            try {
                val result = repository.getProperties()
                _response.value = result
                Log.d("Response in VM", _response.value.toString())
            } catch (e: Exception) {

            }
        }
    }

    fun searchProperty(search : String) : List<Data>{
        val trimmedSearch = search.trim().lowercase()
        val filteredList = _response.value?.data?.filter {
            it.propertyTypeFullDescription.trim().lowercase().contains(trimmedSearch)
        }
        return filteredList ?: emptyList()
    }

    fun getPropertyById(id: String): Data? {
        val actualId = id.toIntOrNull() ?: return null
        val result = _response.value?.data?.find {
            it.id == actualId
        }
        return result
    }
}