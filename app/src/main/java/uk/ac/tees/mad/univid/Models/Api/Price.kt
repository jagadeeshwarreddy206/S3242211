package uk.ac.tees.mad.univid.Models.Api

data class Price(
    val amount: Int,
    val currencyCode: String,
    val displayPrices: List<DisplayPrice>,
    val frequency: String
)