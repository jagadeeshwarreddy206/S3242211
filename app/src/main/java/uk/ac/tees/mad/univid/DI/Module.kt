package uk.ac.tees.mad.univid.DI

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.ac.tees.mad.univid.Data.RealEstateApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    fun providesAuthentication() : FirebaseAuth = Firebase.auth

    @Provides
    fun providesFirestore() : FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://uk-real-estate-rightmove.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesApi() : RealEstateApi {
        return provideRetrofit().create(RealEstateApi::class.java)
    }
}