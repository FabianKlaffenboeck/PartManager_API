package at.fklab.services

import at.fklab.model.ElectricalUnit

class ElectricalUnitService {

    fun getAll(): List<ElectricalUnit>{
        return ElectricalUnit.values().toList()
    }

}