package uk.ac.tees.mad.univid.Models

data class User(
    val profilePictureUrl : String = "",
    val id : String = "",
    val name : String = "",
    val email : String = "",
    val phoneNumber : String = "",
    val password : String = ""
)
