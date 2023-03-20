package pl.brightinventions.persistance.person

import org.jetbrains.exposed.dao.id.IntIdTable

object PersonTable : IntIdTable("person") {
    val name = text("name")
    val surname = text("surname")
    val age = integer("age")
}

object AddressTable : IntIdTable("address") {
    val personId = reference("person_id", PersonTable.id)
    val street = text("street")
    val house = text("house")
    val apartment = text("apartment")
    val city = text("city")
    val postalCode = text("postal_code")
}
