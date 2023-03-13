package pl.brightinventions.dto

data class CreateAddressDto(
    val street: String,
    val house: String,
    val apartment: String,
    val city: String,
    val postalCode: String
)
