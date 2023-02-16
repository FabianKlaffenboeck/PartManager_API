package com.example.routes

import com.example.model.Shelf
import com.example.services.ShelfService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.ShelfRoute(shelfService: ShelfService) {
    routing {
        route("/shelf") {
            get {
                call.respond(shelfService.getAll())
            }
            get("{id}") {
                val id: Int = (call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)).toInt()
                val shelf = shelfService.getById(id) ?: return@get call.respond(HttpStatusCode.NotFound)
                call.respond(shelf)
            }
            post {
                val shelf = call.receive<Shelf>()
                if (shelf.id == null) {
                    return@post call.respond(
                        shelfService.add(shelf, "TBD")
                    )
                }
                call.respond(shelfService.update(shelf, "TBD"))
            }
            delete("{id}") {
                val id: Int = (call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)).toInt()
                call.respond(shelfService.delite(id, "TBD"))
            }
            put {
                call.respond(HttpStatusCode.NotImplemented)
            }
        }
    }
}