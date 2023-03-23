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
    embeddedServer(Netty, port = 8080, host = "127.0.0.1", module = Application::module).start(wait = true)
}

fun Application.module() {

    val dbUrl: String = System.getenv("dbUrl")
    val dbUser: String = System.getenv("dbUser")
    val dbDriver: String = System.getenv("dbDriver")
    val dbPW: String = System.getenv("dbPW")

    configureDatabases(dbUrl, dbUser, dbDriver, dbPW)
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
