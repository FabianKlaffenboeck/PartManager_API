package com.example

import com.example.routes.UserRoute
import com.example.services.UserService
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module).start(wait = true)
}

fun Application.module() {
    configureDatabases()
    configureHTTP()
    configureSerialization()

    defaultRoute()
    UserRoute(UserService())

//    configureSecurity()
}
