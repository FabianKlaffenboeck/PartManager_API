package at.eWolveLabs

import at.eWolveLabs.plugins.configureDatabases
import at.eWolveLabs.plugins.configureHTTP
import at.eWolveLabs.plugins.configureRouting
import at.eWolveLabs.plugins.configureSecurity
import at.eWolveLabs.plugins.configureSerialization
import at.eWolveLabs.plugins.loadJwtConfig
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(
        Netty, port = 8080, module = Application::module
    ).start(wait = true)
}

fun Application.module() {

    val dbUrl: String = System.getenv("DBURL") ?: "jdbc:sqlite:Myridan"

    val dbUser: String = System.getenv("DBUSER") ?: "root"
    val dbPW: String = System.getenv("DBPW") ?: ""

    val initDB: Boolean = System.getenv("INITDB").toBoolean()
    val populateDB: Boolean = System.getenv("POPULATEDB").toBoolean()
    val updateSchema: Boolean = System.getenv("UPDATESCHEMA").toBoolean()

    val jwtConfig = loadJwtConfig()

    configureDatabases(dbUrl, dbUser, dbPW, updateSchema, initDB, populateDB)
    configureHTTP()
    configureSerialization()

    configureSecurity(jwtConfig)

    configureRouting(jwtConfig)
}
