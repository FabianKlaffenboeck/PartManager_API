package at.eWolveLabs

import at.eWolveLabs.plugins.configureDatabases
import at.eWolveLabs.plugins.configureHTTP
import at.eWolveLabs.plugins.configureSecurity
import at.eWolveLabs.plugins.configureSerialization
import at.eWolveLabs.plugins.loadJwtConfig
import at.eWolveLabs.routes.*
import at.eWolveLabs.services.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.response.*
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

    val jwtConfig = loadJwtConfig()

    configureDatabases(dbUrl, dbUser, dbPW, updateSchema, initDB, populateDB)
    configureHTTP()
    configureSerialization()
    configureSecurity(jwtConfig)

    routing {
        get("/") {
            call.respond("Hello from an eWolveLabs product")
        }
        route("/api") {
            loginRoute(jwtConfig)

            authenticate("basicAuth", "auth-jwt") {
                openAPI(path = "openapi")
                swaggerUI(path = "swaggerui")


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
