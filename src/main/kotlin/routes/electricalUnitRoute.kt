package at.eWolveLabs.routes

import at.eWolveLabs.model.ElectricalUnit
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.electricalUnitRoute() {
    route("/electricalUnits") {
        get {
            call.respond(ElectricalUnit.entries)
        }
    }
}