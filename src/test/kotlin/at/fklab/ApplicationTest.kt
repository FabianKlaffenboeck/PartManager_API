package at.fklab

import at.fklab.plugins.defaultRoute
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        routing {
            defaultRoute()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("PartManager API", bodyAsText())
        }
    }
}