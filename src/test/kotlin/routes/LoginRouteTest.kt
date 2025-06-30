package routes

import io.ktor.client.request.*
import io.ktor.server.testing.*
import kotlin.test.Test

class LoginRouteTest {

    @Test
    fun testPostLogin() = testApplication {
        setupTestApp()
        client.post("api/login").apply {
//            TODO("Please write your test here")
        }
    }
}