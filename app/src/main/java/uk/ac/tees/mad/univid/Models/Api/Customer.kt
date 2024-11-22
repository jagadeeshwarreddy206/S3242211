package uk.ac.tees.mad.univid.Models.Api

data class Customer(
    val branchDisplayName: String,
    val branchId: Int,
    val branchLandingPageUrl: String,
    val branchName: String,
    val brandPlusLogoURI: String,
    val brandPlusLogoUrl: String,
    val brandTradingName: String,
    val buildToRent: Boolean,
    val buildToRentBenefits: List<Any>,
    val commercial: Boolean,
    val contactTelephone: String,
    val development: Boolean,
    val developmentContent: Any,
    val enhancedListing: Boolean,
    val showOnMap: Boolean,
    val showReducedProperties: Boolean
)