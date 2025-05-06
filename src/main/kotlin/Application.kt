package at.eWolveLabs

import at.eWolveLabs.plugins.configureDatabases
import at.eWolveLabs.plugins.configureHTTP
import at.eWolveLabs.plugins.configureSecurity
import at.eWolveLabs.plugins.configureSerialization
import at.eWolveLabs.routes.*
import at.eWolveLabs.services.*
import io.ktor.server.application.*
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.authentication
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(
        Netty, port = 8080, module = Application::module
    ).start(wait = true)
}

fun Application.module() {

    val dbUrl: String = System.getenv("DBURL") ?: "jdbc:sqlite:TestDB"

    val dbUser: String = System.getenv("DBUSER") ?: "root"
    val dbPW: String = System.getenv("DBPW") ?: ""

    val initDB: Boolean = System.getenv("INITDB").toBoolean()
    val populateDB: Boolean = System.getenv("POPULATEDB").toBoolean()
    val updateSchema: Boolean = System.getenv("UPDATESCHEMA").toBoolean()

    configureDatabases(dbUrl, dbUser, dbPW, updateSchema, initDB, populateDB)
    configureHTTP()
    configureSerialization()
    configureSecurity()

    routing {
        route("/api") {
            authenticate("basicAuth") {
                swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml")
                manufacturerRoute(ManufacturerService())
                partRoute(PartService())
                partTypeRoute(PartTypeService())
                shelfRoute(ShelfService())
                trayRoute(TrayService())
                footprintRoute(FootprintService())
                electricalUnitRoute()
            }
        }
    }
}
