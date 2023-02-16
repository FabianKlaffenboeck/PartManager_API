package com.example.routes

import com.example.model.Part
import com.example.services.PartService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.PartRoute(partService: PartService) {
    routing {
        route("/part") {
            get {
                call.respond(partService.getAll())
            }
            get("{id}") {
                val id: Int = (call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)).toInt()
                val part = partService.getById(id) ?: return@get call.respond(HttpStatusCode.NotFound)
                call.respond(part)
            }
            post {
                val part = call.receive<Part>()
                if (part.id == null) {
                    return@post call.respond(
                        partService.add(part, "TBD")
                    )
                }
                call.respond(partService.update(part, "TBD"))
            }
            delete("{id}") {
                val id: Int = (call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)).toInt()
                call.respond(partService.delite(id, "TBD"))
            }
            put {
                call.respond(HttpStatusCode.NotImplemented)
            }
        }
    }
}