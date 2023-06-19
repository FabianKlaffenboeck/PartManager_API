package at.fklab.development

import at.fklab.model.Footprint
import at.fklab.model.MeasurementUnit
import at.fklab.model.Part

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
        measurementUnit = MeasurementUnit.Ohm,
        value = 564.00,
        footprint = Footprint._6332,
        partType = samplePartTypes[0],
        manufacturer = sampleManufacturers[1],
        tray = sampleTrays[1],
    ) ,
    Part(
        id = 3,
        name = "NCP1117",
        quantity = 3,
        measurementUnit = MeasurementUnit.Farad,
        value = 100.00,
        footprint = Footprint._6332,
        partType = samplePartTypes[0],
        manufacturer = sampleManufacturers[2],
        tray = sampleTrays[2],
    )
)