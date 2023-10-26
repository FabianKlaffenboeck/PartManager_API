package at.fklab.routes

import at.fklab.model.MeasurementUnit
import at.fklab.services.MeasurementUnitService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.measurementUnitRoute(measurementUnitService: MeasurementUnitService) {
    route("/measurementUnits") {
        get {
            call.respond(measurementUnitService.getAll())
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