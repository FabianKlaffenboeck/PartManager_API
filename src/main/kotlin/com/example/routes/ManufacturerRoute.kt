package com.example.routes

import com.example.model.Manufacturer
import com.example.services.ManufacturerService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.ManufacturerRoute(manufacturerService: ManufacturerService) {
    route("/manufacturer") {
        get {
            call.respond(manufacturerService.getAll())
        }
        get("{id}") {
            val id: Int = (call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)).toInt()
            val manufacturer = manufacturerService.getById(id) ?: return@get call.respond(HttpStatusCode.NotFound)
            call.respond(manufacturer)
        }
        post {
            val manufacturer = call.receive<Manufacturer>()
            if (manufacturer.id == null) {
                return@post call.respond(
                    manufacturerService.add(manufacturer, "TBD")
                )
            }
            call.respond(manufacturerService.update(manufacturer, "TBD"))
        }
        delete("{id}") {
            val id: Int = (call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)).toInt()
            call.respond(manufacturerService.delite(id, "TBD"))
        }
        put {
            call.respond(HttpStatusCode.NotImplemented)
        }
    }

}