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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import uk.ac.tees.mad.univid.Data.PropertyData
import uk.ac.tees.mad.univid.Models.Api.ApiResponse
import uk.ac.tees.mad.univid.Models.Api.Customer
import uk.ac.tees.mad.univid.Models.Api.Data
import uk.ac.tees.mad.univid.Models.Api.ListingUpdate
import uk.ac.tees.mad.univid.Models.Api.Location
import uk.ac.tees.mad.univid.Models.Api.LozengeModel
import uk.ac.tees.mad.univid.Models.Api.Price
import uk.ac.tees.mad.univid.Models.Api.ProductLabel
import uk.ac.tees.mad.univid.Models.Api.PropertyImages
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

    private val _propertyData = MutableLiveData<List<PropertyData>>()
    val propertyData: LiveData<List<PropertyData>> = _propertyData

    val userData = mutableStateOf<User?>(null)

    init {
        if (auth.currentUser != null){
            isSignedIn.value = true
            getUserData()
        }
        fetchData()
    }

    private fun getUserData() {
        firestore.collection("users").document(auth.currentUser!!.uid).get().addOnSuccessListener {
            val user = it.toObject(User::class.java)
            userData.value = user
        }.addOnFailureListener {
            Log.d("User Data", "getUserData: ${it.message}")
        }
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

    fun insertProperty(data: Data) {
        val newData = data.toPropertyData()
        viewModelScope.launch {
            repository.insertProperty(newData)
        }
    }

    fun deleteProperty(data: PropertyData) {
        viewModelScope.launch {
            repository.deleteProperty(data)
        }
    }

    fun getProperties(){
        viewModelScope.launch {
            repository.getAllProperty().collect {
                _propertyData.value = it
            }
        }
    }

    fun Data.toPropertyData(): PropertyData{
        return PropertyData(
            addedOrReduced = this.addedOrReduced,
         auction = this.auction,
         bathrooms = this.bathrooms,
         bedrooms = this.bedrooms,
         channel = this.channel,
         commercial = this.commercial,
         contactUrl = this.contactUrl,
         countryCode = this.countryCode,
         customer = this.customer,
         development = this.development,
         displayAddress = this.displayAddress,
         displaySize = this.displaySize,
         displayStatus = this.displayStatus,
         enhancedListing = this.enhancedListing,
         featuredProperty = this.featuredProperty,
         feesApply = this.feesApply,
         firstVisibleDate = this.firstVisibleDate,
         formattedBranchName = this.formattedBranchName,
         formattedDistance = this.formattedDistance,
         hasBrandPlus = this.hasBrandPlus,
         heading = this.heading,
         hidden = this.hidden,
         id = this.id,
         isRecent = this.isRecent,
         keywordMatchType = this.keywordMatchType,
         listingUpdate = this.listingUpdate,
         location = this.location,
         lozengeModel = this.lozengeModel,
         numberOfFloorplans = this.numberOfFloorplans,
         numberOfImages = this.numberOfImages,
         numberOfVirtualTours = this.numberOfVirtualTours,
         onlineViewingsAvailable = this.onlineViewingsAvailable,
         premiumListing = this.premiumListing,
         price = this.price,
         productLabel = this.productLabel,
         propertyImages = this.propertyImages,
         propertySubType = this.propertySubType,
         propertyTypeFullDescription = this.propertyTypeFullDescription,
         propertyUrl = this.propertyUrl,
         residential = this.residential,
         saved = this.saved,
         showOnMap = this.showOnMap,
         students = this.students,
         summary = this.summary,
         transactionType = this.transactionType
        )
    }

    fun logout() {
        auth.signOut()
        isSignedIn.value = false
    }
}