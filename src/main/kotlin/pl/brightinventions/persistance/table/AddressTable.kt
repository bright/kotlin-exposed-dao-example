package pl.brightinventions.persistance.table

import org.jetbrains.exposed.sql.Table

object AddressTable : Table("address") {
    val id = integer("id").autoIncrement().uniqueIndex()
    val personId = reference("person_id", PersonTable.id)
    val street = text("street")
    val house = text("house")
    val apartment = text("apartment")
    val city = text("city")
    val postalCode = text("postal_code")
}
