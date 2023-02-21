package com.example.development

import com.example.model.PartType
import com.example.model.measurementUnit

val samplePartTypes = listOf(
    PartType(
        id = 1,
        name = "Controller",
        unit = null
    ),
    PartType(
        id = 2,
        name = "Resistor",
        unit = measurementUnit.Ohm
    ),
)