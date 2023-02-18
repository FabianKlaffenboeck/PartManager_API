package com.example.development

import com.example.model.Part
import com.example.model.User
import java.time.LocalDateTime

val sampleParts = listOf(
    Part(
        id = 1,
        name = "Mcp 2515",
        quantity = 3,
        partType = samplePartTypes[0],
        manufacturer = sampleManufacturers[1],
        tray = sampleTrays[0],
    ) ,
    Part(
        id = 2,
        name = "Mcp 2552",
        quantity = 3,
        partType = samplePartTypes[0],
        manufacturer = sampleManufacturers[1],
        tray = sampleTrays[1],
    ) ,
    Part(
        id = 3,
        name = "NCP1117",
        quantity = 3,
        partType = samplePartTypes[0],
        manufacturer = sampleManufacturers[2],
        tray = sampleTrays[2],
    )
)