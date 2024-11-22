package uk.ac.tees.mad.univid.Models.Api

data class PropertyImages(
    val images: List<Image>,
    val mainImageSrc: String,
    val mainMapImageSrc: String
)