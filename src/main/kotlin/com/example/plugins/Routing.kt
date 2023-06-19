package com.example.plugins

import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*

fun Route.defaultRoute() {
    get {
        call.respondText("The PartManager API")
    }
}
