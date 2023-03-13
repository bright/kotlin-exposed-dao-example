package pl.brightinventions
import pl.brightinventions.plugins.configureData
import pl.brightinventions.plugins.configureRouting
import io.ktor.server.application.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(ContentNegotiation) {
            json()
        }
        configureRouting()
        configureData()
    }
        .start(wait = true)
}
