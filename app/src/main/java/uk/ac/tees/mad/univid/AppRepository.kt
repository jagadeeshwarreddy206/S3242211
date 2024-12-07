package uk.ac.tees.mad.univid

import android.util.Log
import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.univid.Data.PropertyDao
import uk.ac.tees.mad.univid.Data.PropertyData
import uk.ac.tees.mad.univid.Data.RealEstateApi
import uk.ac.tees.mad.univid.Models.Api.ApiResponse
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val api : RealEstateApi,
    private val propertyDao: PropertyDao
) {
    suspend fun getProperties() : ApiResponse {
        val response = api.getPropertiesForSale(
            apiKey = "06c0a77c2amsh76d45084212ec73p19808cjsn35783564d7d1",
            apiHost = "uk-real-estate-rightmove.p.rapidapi.com",
            identifier = "REGION^1036",
            sortBy = "HighestPrice",
            searchRadius = 0.0f,
            addedToSite = 1
        )
        Log.d("Response",response.toString())
        return response
    }

    suspend fun insertProperty(data : PropertyData){
        propertyDao.insertProperty(data)
    }

    suspend fun deleteProperty(data: PropertyData){
        propertyDao.deleteProperty(data)
    }

    fun getAllProperty() : Flow<List<PropertyData>> {
        val response = propertyDao.getAllProperties()
        return response
    }
}