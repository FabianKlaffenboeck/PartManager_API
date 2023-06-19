package at.fklab

import at.fklab.plugins.configureDatabases
import at.fklab.plugins.configureHTTP
import at.fklab.plugins.configureSerialization
import at.fklab.plugins.defaultRoute
import at.fklab.routes.*
import at.fklab.services.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {

    val dbUrl: String = System.getenv("DBURL") ?: "jdbc:sqlite:TestDB"
    val dbUser: String = System.getenv("DBUSER") ?: "root"
    val dbPW: String = System.getenv("DBPW") ?: ""
    val initDB: Boolean = System.getenv("INITDB").toBoolean()
    val populateDB: Boolean = System.getenv("POPULATEDB").toBoolean()

    configureDatabases(dbUrl, dbUser, dbPW, initDB, populateDB)
    configureHTTP()
    configureSerialization()

    routing {
        route("/api") {
            defaultRoute()
            ManufacturerRoute(ManufacturerService())
            PartRoute(PartService())
            PartTypeRoute(PartTypeService())
            ShelfRoute(ShelfService())
            TrayRoute(TrayService())
            UserRoute(UserService())
        }
    }


//    configureSecurity()
}
