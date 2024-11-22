package uk.ac.tees.mad.univid.Data

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import uk.ac.tees.mad.univid.Models.Api.ApiResponse

interface RealEstateApi {
    @GET("buy/property-for-sale")
    suspend fun getPropertiesForSale(
        @Header("x-rapidapi-key") apiKey: String,
        @Header("x-rapidapi-host") apiHost: String,
        @Query("identifier") identifier: String,
        @Query("sort_by") sortBy: String,
        @Query("search_radius") searchRadius: Float,
        @Query("added_to_site") addedToSite: Int
    ): ApiResponse
}