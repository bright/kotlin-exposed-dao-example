package pl.brightinventions.persistance.table

import org.jetbrains.exposed.sql.Table

object PersonTable : Table("person") {
    val id = integer("id").autoIncrement().uniqueIndex()
    val name = text("name")
    val surname = text("surname")
    val age = integer("age")
}
