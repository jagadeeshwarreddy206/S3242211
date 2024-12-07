package uk.ac.tees.mad.univid

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import uk.ac.tees.mad.univid.Models.Api.Customer
import uk.ac.tees.mad.univid.Models.Api.ListingUpdate
import uk.ac.tees.mad.univid.Models.Api.Location
import uk.ac.tees.mad.univid.Models.Api.LozengeModel
import uk.ac.tees.mad.univid.Models.Api.Price
import uk.ac.tees.mad.univid.Models.Api.ProductLabel
import uk.ac.tees.mad.univid.Models.Api.PropertyImages

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromCustomer(customer: Customer?): String? {
        return gson.toJson(customer)
    }

    @TypeConverter
    fun toCustomer(customerString: String?): Customer? {
        return gson.fromJson(customerString, Customer::class.java)
    }

    @TypeConverter
    fun fromListingUpdate(listingUpdate: ListingUpdate): String {
        return gson.toJson(listingUpdate)
    }

    @TypeConverter
    fun toListingUpdate(listingUpdateString: String): ListingUpdate {
        return gson.fromJson(listingUpdateString, ListingUpdate::class.java)
    }

    @TypeConverter
    fun fromLocation(location: Location): String {
        return gson.toJson(location)
    }

    @TypeConverter
    fun toLocation(locationString: String): Location {
        return gson.fromJson(locationString, Location::class.java)
    }

    @TypeConverter
    fun fromLozengeModel(lozengeModel: LozengeModel): String {
        return gson.toJson(lozengeModel)
    }

    @TypeConverter
    fun toLozengeModel(lozengeModelString: String): LozengeModel {
        return gson.fromJson(lozengeModelString, LozengeModel::class.java)
    }

    @TypeConverter
    fun fromPrice(price: Price): String {
        return gson.toJson(price)
    }

    @TypeConverter
    fun toPrice(priceString: String): Price {
        return gson.fromJson(priceString, Price::class.java)
    }

    @TypeConverter
    fun fromProductLabel(productLabel: ProductLabel): String {
        return gson.toJson(productLabel)
    }

    @TypeConverter
    fun toProductLabel(productLabelString: String): ProductLabel {
        return gson.fromJson(productLabelString, ProductLabel::class.java)
    }

    @TypeConverter
    fun fromPropertyImages(propertyImages: PropertyImages): String {
        return gson.toJson(propertyImages)
    }

    @TypeConverter
    fun toPropertyImages(propertyImagesString: String): PropertyImages {
        return gson.fromJson(propertyImagesString, PropertyImages::class.java)
    }

    @TypeConverter
    fun fromList(list: List<Any>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toList(listString: String): List<Any> {
        val listType = object : TypeToken<List<Any>>() {}.type
        return gson.fromJson(listString, listType)
    }

    @TypeConverter
    fun fromAny(any: Any?): String? {
        return gson.toJson(any)
    }

    @TypeConverter
    fun toAny(anyString: String?): Any? {
        return gson.fromJson(anyString, Any::class.java)
    }
}