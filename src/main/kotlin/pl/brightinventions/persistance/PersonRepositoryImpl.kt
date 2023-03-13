package pl.brightinventions.persistance

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import pl.brightinventions.dto.*
import pl.brightinventions.persistance.table.AddressTable
import pl.brightinventions.persistance.table.PersonTable

class PersonRepositoryImpl : PersonRepository {

    override fun findAll(): List<FoundPersonDto> = transaction {
        PersonTable.selectAll().map {
            FoundPersonDto(
                it[PersonTable.id],
                it[PersonTable.name],
                it[PersonTable.surname],
                it[PersonTable.age]
            )
        }
    }

    override fun find(id: PersonId): FoundPersonWithAddressDto? = transaction {
        val addresses = AddressTable
            .select { AddressTable.personId eq id }
            .map {
                FoundPersonAddressDto(
                    it[AddressTable.street],
                    it[AddressTable.house],
                    it[AddressTable.apartment],
                    it[AddressTable.city],
                    it[AddressTable.postalCode]
                )
            }

        PersonTable.select { PersonTable.id eq id }.firstOrNull()?.let {
            FoundPersonWithAddressDto(
                it[PersonTable.id],
                it[PersonTable.name],
                it[PersonTable.surname],
                it[PersonTable.age],
                addresses
            )
        }
    }

    override fun create(person: CreatePersonDto): PersonId = transaction {
        PersonTable.insert {
            it[name] = person.name
            it[surname] = person.surname
            it[age] = person.age
        }.resultedValues!!.map { it[PersonTable.id] }.first()
    }

    override fun delete(id: PersonId): Unit = transaction {
        PersonTable.deleteWhere {
            PersonTable.id eq id
        }
    }

    override fun update(id: PersonId, person: UpdatePersonDto) {
        PersonTable.update({ PersonTable.id eq id }) {
            it[age] = person.age
            it[name] = person.name
            it[surname] = person.surname
        }
    }

    override fun addAddress(personId: PersonId, address: CreateAddressDto) {
        AddressTable.insert {
            it[AddressTable.personId] = personId
            it[street] = address.street
            it[city] = address.city
            it[house] = address.house
            it[postalCode] = address.postalCode
            it[apartment] = address.apartment
        }
    }
}
