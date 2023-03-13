package pl.brightinventions.persistance

import pl.brightinventions.dto.*

interface PersonRepository {

    fun findAll(): List<FoundPersonDto>
    fun find(id: PersonId): FoundPersonWithAddressDto?
    fun create(person: CreatePersonDto): PersonId
    fun delete(id: PersonId)
    fun update(id: PersonId, person: UpdatePersonDto)
    fun addAddress(personId: PersonId, address: CreateAddressDto)
}
