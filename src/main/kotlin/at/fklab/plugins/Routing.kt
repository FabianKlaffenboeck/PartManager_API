package at.fklab.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.defaultRoute() {
    get {
        call.respondText("PartManager API")
    }
}
