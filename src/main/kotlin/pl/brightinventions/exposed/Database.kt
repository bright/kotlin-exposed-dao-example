package pl.brightinventions.exposed

import org.jetbrains.exposed.sql.Database

class Database {

    companion object {
        fun register() {
            Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")
        }
    }
}
