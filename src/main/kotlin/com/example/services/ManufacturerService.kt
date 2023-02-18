package com.example.services

import com.example.model.Manufacturer
import com.example.model.ManufacturerEntity
import com.example.model.Manufacturers
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class ManufacturerService {

    fun getAll(): List<Manufacturer> = transaction {
        val query = Op.build { Manufacturers.deletedAt.isNull() }
        ManufacturerEntity.find(query).map(ManufacturerEntity::toManufacturer)
    }

    fun getById(id: Int): Manufacturer? = transaction {
        ManufacturerEntity.find {
            Manufacturers.id eq id
        }.firstOrNull()?.toManufacturer()
    }

    fun add(manufacturer: Manufacturer, user: String): Manufacturer = transaction {
        ManufacturerEntity.new {
            name = manufacturer.name

            updatedAt = LocalDateTime.now()
            updatedBy = user
        }.toManufacturer()
    }

    fun update(manufacturer: Manufacturer, user: String): Manufacturer = transaction {
        val notNullId = manufacturer.id ?: -1

        ManufacturerEntity[notNullId].name = manufacturer.name

        ManufacturerEntity[notNullId].updatedAt = LocalDateTime.now()
        ManufacturerEntity[notNullId].updatedBy = user
        ManufacturerEntity[notNullId].toManufacturer()
    }

    fun delite(id: Int, user: String) = transaction {
        ManufacturerEntity[id].deletedAt = LocalDateTime.now()
        ManufacturerEntity[id].deletedBy = user
    }
}