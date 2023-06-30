package at.fklab.services

import at.fklab.model.*
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class PartService {

    fun getAll(): List<Part> = transaction {
        val query = Op.build { Parts.deletedAt.isNull() }
        PartEntity.find(query).map(PartEntity::toPart)
    }

    fun getById(id: Int): Part? = transaction {
        PartEntity.find {
            Parts.id eq id
        }.firstOrNull()?.toPart()
    }

    fun add(part: Part): Part = transaction {
        PartEntity.new {
            name = part.name
            quantity = part.quantity
            measurementUnit = part.measurementUnit
            value = part.value
            footprint = part.footprint
            partType = part.partType.id?.let { PartTypeEntity.findById(it) }!!
            manufacturer = part.manufacturer.id?.let { ManufacturerEntity.findById(it) }!!
            tray = part.tray.id?.let { TrayEntity.findById(it) }!!

            updatedAt = LocalDateTime.now()
        }.toPart()
    }


    fun update(part: Part): Part = transaction {
        val notNullId = part.id ?: -1

        PartEntity[notNullId].name = part.name
        PartEntity[notNullId].quantity = part.quantity
        PartEntity[notNullId].measurementUnit = part.measurementUnit
        PartEntity[notNullId].value = part.value
        PartEntity[notNullId].footprint = part.footprint
        PartEntity[notNullId].partType = part.partType.id?.let { PartTypeEntity.findById(it) }!!
        PartEntity[notNullId].manufacturer = part.manufacturer.id?.let { ManufacturerEntity.findById(it) }!!
        PartEntity[notNullId].tray = part.tray.id?.let { TrayEntity.findById(it) }!!

        PartEntity[notNullId].updatedAt = LocalDateTime.now()
        PartEntity[notNullId].toPart()
    }

    fun delite(id: Int) = transaction {
        PartEntity[id].deletedAt = LocalDateTime.now()
    }
}