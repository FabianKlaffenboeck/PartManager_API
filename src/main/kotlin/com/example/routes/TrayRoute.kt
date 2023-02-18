package com.example.routes

import com.example.model.Tray
import com.example.services.TrayService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.TrayRoute(trayService: TrayService) {
    routing {
        route("/tray") {
            get {
                call.respond(trayService.getAll())
            }
            get("{id}") {
                val id: Int = (call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)).toInt()
                val tray = trayService.getById(id) ?: return@get call.respond(HttpStatusCode.NotFound)
                call.respond(tray)
            }
            post {
                val tray = call.receive<Tray>()
                if (tray.id == null) {
                    return@post call.respond(
                        trayService.add(tray, "TBD")
                    )
                }
                call.respond(trayService.update(tray, "TBD"))
            }
            delete("{id}") {
                val id: Int = (call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)).toInt()
                call.respond(trayService.delite(id, "TBD"))
            }
            put {
                call.respond(HttpStatusCode.NotImplemented)
            }
        }
    }
}