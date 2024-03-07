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
            val id: Int = (call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)).toInt()
            val measurementUnit = measurementUnitService.getById(id) ?: return@get call.respond(HttpStatusCode.NotFound)
            call.respond(measurementUnit)
        }
        post {
            val measurementUnit = call.receive<MeasurementUnit>()
            if (measurementUnit.id == null) {
                return@post call.respond(
                    measurementUnitService.add(measurementUnit)
                )
            }
            call.respond(measurementUnitService.update(measurementUnit))
        }
        delete("{id}") {
            val id: Int = (call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)).toInt()
            call.respond(measurementUnitService.delete(id))
        }
    }
}