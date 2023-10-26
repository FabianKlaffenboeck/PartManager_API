package at.fklab.routes

import at.fklab.services.FootprintService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.footprintRoute(footprintService: FootprintService) {
    route("/footprints") {
        get {
            call.respond(footprintService.getAll())
            call.respond(HttpStatusCode.NotImplemented)
        }
        get("{id}") {
            call.respond(HttpStatusCode.NotImplemented)
        }
        post {
            call.respond(HttpStatusCode.NotImplemented)
        }
        put {
            call.respond(HttpStatusCode.NotImplemented)
        }
        delete("{id}") {
            call.respond(HttpStatusCode.NotImplemented)
        }
    }
}