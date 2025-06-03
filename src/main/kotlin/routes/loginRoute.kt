package at.eWolveLabs.routes

import at.eWolveLabs.plugins.JwtConfig
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

data class LoginRequest(val username: String, val password: String)
data class LoginResponse(val token: String)

fun Route.loginRoute(jwtConfig: JwtConfig) {
    route("/login") {
        post {
            val request = call.receive<LoginRequest>()
            if (request.username.isNotEmpty() && request.password.isNotEmpty()) {
                val token = JWT.create()
                    .withAudience(jwtConfig.audience)
                    .withIssuer(jwtConfig.domain)
                    .withClaim("username", request.username)
                    .sign(Algorithm.HMAC256(jwtConfig.secret))

                call.respond(LoginResponse(token))
            } else {
                call.respond(HttpStatusCode.Unauthorized, "Invalid credentials")
            }
        }
    }
}