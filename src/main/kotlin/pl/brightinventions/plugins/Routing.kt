package pl.brightinventions.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import pl.brightinventions.persistance.person.PersonRepository

fun Application.configureRouting() {
    val repository = PersonRepository()

    routing {
        route("/person") {
            get {
                call.respond(repository.findAll())
            }
            get("/{id}") {
                val found = repository.find(call.parameters["id"]?.toInt()!!)
                found?.let { call.respond(it) } ?: call.respond(HttpStatusCode.NotFound)
            }
            post {
                call.respond(repository.create(call.receive()))
            }
            delete("/{id}") {
                call.respond(repository.delete(call.parameters["id"]?.toInt()!!))
            }
            put("/{id}") {
                call.respond(repository.update(call.parameters["id"]?.toInt()!!, call.receive()))
            }
        }
    }
}
