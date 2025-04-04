package at.fklab.routes

import at.fklab.services.ElectricalUnitService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.electricalUnitRoute(measurementUnitService: ElectricalUnitService) {
    route("/measurementUnits") {
        get {
            call.respond(measurementUnitService.getAll())
        }
    }
}