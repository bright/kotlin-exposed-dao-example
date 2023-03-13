package pl.brightinventions.dto

import kotlinx.serialization.Serializable

@Serializable
data class FoundPersonDto(
    val id: Int,
    val name: String,
    val surname: String,
    val age: Int
)

@Serializable
data class FoundPersonWithAddressDto(
    val id: Int,
    val name: String,
    val surname: String,
    val age: Int,
    val addresses: List<FoundPersonAddressDto>
)

@Serializable
data class FoundPersonAddressDto(
    val street: String,
    val house: String,
    val apartment: String,
    val city: String,
    val postalCode: String
)
