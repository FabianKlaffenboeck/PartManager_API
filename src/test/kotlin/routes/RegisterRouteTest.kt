package routes

import io.ktor.client.request.*
import io.ktor.server.testing.*
import kotlin.test.Test

class RegisterRouteTest {

    @Test
    fun testPostRegister() = testApplication {
        setupTestApp()
        client.post("api/register").apply {
//            TODO("Please write your test here")
        }
    }
}