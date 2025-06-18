package at.eWolveLabs.routes

import at.eWolveLabs.plugins.JwtConfig
import at.eWolveLabs.services.PasswordHasher
import at.eWolveLabs.services.UserService
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

data class LoginRequest(val username: String, val password: String)

fun Route.loginRoute(jwtConfig: JwtConfig, userService: UserService) {
    route("/login") {
        post {

            val req = call.receive<LoginRequest>()

            val user = userService.getByUsername(req.username)

            if (user == null) {
                return@post call.respond(HttpStatusCode.Unauthorized)
            }

            if (!PasswordHasher.verify(req.password, user.passwordHash)) {
                return@post call.respond(HttpStatusCode.Unauthorized)
            }

            val token = JWT.create()
                .withAudience(jwtConfig.audience)
                .withIssuer(jwtConfig.domain)
                .withClaim("username", req.username)
                .sign(Algorithm.HMAC256(jwtConfig.secret))

            call.respond(mapOf("token" to token))
        }
    }
}
