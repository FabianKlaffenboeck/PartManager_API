package at.eWolveLabs.development

import at.eWolveLabs.model.Footprint
import at.eWolveLabs.model.MeasurementUnit
import at.eWolveLabs.model.Part

val sampleParts = listOf(
    Part(
        id = 1,
        name = "Mcp 2515",
        quantity = 3,
        measurementUnit = null,
        value = null,
        footprint = null,
        partType = samplePartTypes[0],
        manufacturer = sampleManufacturers[1],
        tray = sampleTrays[0],
    ) ,
    Part(
        id = 2,
        name = "Mcp 2552",
        quantity = 3,
        measurementUnit = sampleMeasurementUnits[0],
        value = 564.00,
        footprint = sampleFootprints[0],
        partType = samplePartTypes[0],
        manufacturer = sampleManufacturers[1],
        tray = sampleTrays[1],
    ) ,
    Part(
        id = 3,
        name = "NCP1117",
        quantity = 3,
        measurementUnit = sampleMeasurementUnits[0],
        value = 100.00,
        footprint = sampleFootprints[0],
        partType = samplePartTypes[0],
        manufacturer = sampleManufacturers[2],
        tray = sampleTrays[2],
    )
)