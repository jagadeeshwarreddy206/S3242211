package uk.ac.tees.mad.univid.Models.Api

data class ApiResponse(
    val currentPage: Int,
    val `data`: List<Data>,
    val message: String,
    val resultsPerPage: Int,
    val status: Boolean,
    val totalResultCount: Int
)

