package com.example.routes

import com.example.model.PartType
import com.example.services.PartTypeService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.PartTypeRoute(partTypeService: PartTypeService) {
    routing {
        route("/partType") {
            get {
                call.respond(partTypeService.getAll())
            }
            get("{id}") {
                val id: Int = (call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)).toInt()
                val partType = partTypeService.getById(id) ?: return@get call.respond(HttpStatusCode.NotFound)
                call.respond(partType)
            }
            post {
                val partType = call.receive<PartType>()
                if (partType.id == null) {
                    return@post call.respond(
                        partTypeService.add(partType, "TBD")
                    )
                }
                call.respond(partTypeService.update(partType, "TBD"))
            }
            delete("{id}") {
                val id: Int = (call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)).toInt()
                call.respond(partTypeService.delite(id, "TBD"))
            }
            put {
                call.respond(HttpStatusCode.NotImplemented)
            }
        }
        route("measurementUnit"){
            get {
                call.respond(partTypeService.getUnits())
            }
        }
    }
}