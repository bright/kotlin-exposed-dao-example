package pl.brightinventions.plugins

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import pl.brightinventions.dto.CreateAddressDto
import pl.brightinventions.dto.CreatePersonDto
import pl.brightinventions.exposed.Database
import pl.brightinventions.persistance.person.PersonRepository
import pl.brightinventions.persistance.person.AddressTable
import pl.brightinventions.persistance.person.PersonTable

fun Application.configureData() {
    Database.register()
    val repository = PersonRepository()
    transaction {
        SchemaUtils.create(PersonTable)
        SchemaUtils.create(AddressTable)
        val john = repository.create(CreatePersonDto("John", "Doe", 33))
        repository.addAddress(john, CreateAddressDto(
            "ul. Jana Matejki", "12", "1", "Gdansk", "80-232"
        ))
        repository.addAddress(john, CreateAddressDto(
            "ul. Jana Matejki", "13", "1", "Gdansk", "80-232"
        ))
        repository.create(CreatePersonDto("George", "Smith", 34))
        repository.create(CreatePersonDto("Megan", "Miller", 22))
    }
}
