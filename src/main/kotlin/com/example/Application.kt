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
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module).start(wait = true)
}

fun Application.module() {
    configureDatabases()
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
