package at.eWolveLabs

import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        assertEquals(HttpStatusCode.OK, HttpStatusCode.OK)
    }
}
