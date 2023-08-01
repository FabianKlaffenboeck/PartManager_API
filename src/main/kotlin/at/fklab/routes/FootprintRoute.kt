package at.fklab.routes

import at.fklab.model.Footprint
import at.fklab.model.MeasurementUnit
import at.fklab.model.Part
import at.fklab.services.PartService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.FootprintRoute() {
    route("/footprints") {
        get {
            call.respond(Footprint.values())
        }
    }
}