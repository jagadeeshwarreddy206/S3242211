package uk.ac.tees.mad.univid.Data

import androidx.room.Entity
import androidx.room.PrimaryKey
import uk.ac.tees.mad.univid.Models.Api.Customer
import uk.ac.tees.mad.univid.Models.Api.ListingUpdate
import uk.ac.tees.mad.univid.Models.Api.Location
import uk.ac.tees.mad.univid.Models.Api.LozengeModel
import uk.ac.tees.mad.univid.Models.Api.Price
import uk.ac.tees.mad.univid.Models.Api.ProductLabel
import uk.ac.tees.mad.univid.Models.Api.PropertyImages

@Entity(tableName = "property_table")
data class PropertyData(
    @PrimaryKey(autoGenerate = true)val iD : Int = 0,
    val addedOrReduced: String,
    val auction: Boolean,
    val bathrooms: Int,
    val bedrooms: Int,
    val channel: String,
    val commercial: Boolean,
    val contactUrl: String,
    val countryCode: String,
    val customer: Customer,
    val development: Boolean,
    val displayAddress: String,
    val displaySize: String,
    val displayStatus: String,
    val distance: Any,
    val enhancedListing: Boolean,
    val enquiredTimestamp: Any,
    val enquiryAddedTimestamp: Any,
    val enquiryCalledTimestamp: Any,
    val featuredProperty: Boolean,
    val feesApply: Boolean,
    val feesApplyText: Any,
    val firstVisibleDate: String,
    val formattedBranchName: String,
    val formattedDistance: String,
    val hasBrandPlus: Boolean,
    val heading: String,
    val hidden: Boolean,
    val id: Int,
    val isRecent: Boolean,
    val keywordMatchType: String,
    val keywords: List<Any>,
    val listingUpdate: ListingUpdate,
    val location: Location,
    val lozengeModel: LozengeModel,
    val numberOfFloorplans: Int,
    val numberOfImages: Int,
    val numberOfVirtualTours: Int,
    val onlineViewingsAvailable: Boolean,
    val premiumListing: Boolean,
    val price: Price,
    val productLabel: ProductLabel,
    val propertyImages: PropertyImages,
    val propertySubType: String,
    val propertyTypeFullDescription: String,
    val propertyUrl: String,
    val residential: Boolean,
    val saved: Boolean,
    val showOnMap: Boolean,
    val staticMapUrl: Any,
    val students: Boolean,
    val summary: String,
    val transactionType: String
)
