package uk.ac.tees.mad.univid.DI

import android.content.Context
import androidx.room.Room
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.ac.tees.mad.univid.Data.PropertyDao
import uk.ac.tees.mad.univid.Data.PropertyDatabase
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

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PropertyDatabase {
        return Room.databaseBuilder(
            context,
            PropertyDatabase::class.java,
            "property_database"
        ).build()
    }

    @Provides
    fun providePropertyDao(database: PropertyDatabase): PropertyDao {
        return database.propertyDao()
    }
}