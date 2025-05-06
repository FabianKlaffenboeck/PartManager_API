package at.eWolveLabs.routes

import at.eWolveLabs.model.ElectricalUnit
import at.eWolveLabs.model.Manufacturer
import at.eWolveLabs.services.ManufacturerService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.electricalUnitRoute() {
    route("/electricalUnits") {
        get {
            call.respond(ElectricalUnit.entries.toTypedArray())
        }
    }
}