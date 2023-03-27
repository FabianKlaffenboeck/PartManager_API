package com.example

import com.example.plugins.configureDatabases
import com.example.plugins.configureHTTP
import com.example.plugins.configureSerialization
import com.example.plugins.defaultRoute
import com.example.routes.*
import com.example.services.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {

    val dbUrl: String = System.getenv("DBURL") ?: "jdbc:sqlite:TestDB"
    val dbUser: String = System.getenv("DBUSER") ?: "root"
    val dbPW: String = System.getenv("DBPW") ?: ""
    val initDB: Boolean = System.getenv("INITDB").toBoolean()
    val populateDB: Boolean = System.getenv("POPULATEDB").toBoolean()

    configureDatabases(dbUrl, dbUser, dbPW,initDB,populateDB)
    configureHTTP()
    configureSerialization()

    defaultRoute()
    ManufacturerRoute(ManufacturerService())
    PartRoute(PartService())
    PartTypeRoute(PartTypeService())
    ShelfRoute(ShelfService())
    TrayRoute(TrayService())
    UserRoute(UserService())

//    configureSecurity()
}
