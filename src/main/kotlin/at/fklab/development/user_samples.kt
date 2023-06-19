package at.fklab.development

import at.fklab.model.User
import java.time.LocalDateTime

val sampleUsers = listOf(
    User(
        id = 1,
        name = "Fabian",
        lastLogin = LocalDateTime.now(),
    )
)