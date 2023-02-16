package com.example.routes

import com.example.model.User
import com.example.services.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.UserRoute(userService: UserService) {
    routing {
        route("/user") {
            get {
                call.respond(userService.getAll())
            }
            get("{id}") {
                val id: Int = (call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)).toInt()
                val user = userService.getById(id) ?: return@get call.respond(HttpStatusCode.NotFound)
                call.respond(user)
            }
            post {
                val user = call.receive<User>()
                if (user.id == null) {
                    return@post call.respond(
                        userService.add(user, "TBD")
                    )
                }
                call.respond(userService.update(user, "TBD"))
            }
            delete("{id}") {
                val id: Int = (call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)).toInt()
                call.respond(userService.delite(id, "TBD"))
            }
            put {
                call.respond(HttpStatusCode.NotImplemented)
            }
        }
    }
}