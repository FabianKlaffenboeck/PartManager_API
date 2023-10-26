package at.fklab.services

import at.fklab.model.Manufacturer
import at.fklab.model.ManufacturerEntity
import at.fklab.model.Manufacturers
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

    fun add(manufacturer: Manufacturer): Manufacturer = transaction {
        ManufacturerEntity.new {
            name = manufacturer.name

            updatedAt = LocalDateTime.now()
        }.toManufacturer()
    }

    fun update(manufacturer: Manufacturer): Manufacturer = transaction {
        val notNullId = manufacturer.id ?: -1

        ManufacturerEntity[notNullId].name = manufacturer.name

        ManufacturerEntity[notNullId].updatedAt = LocalDateTime.now()
        ManufacturerEntity[notNullId].toManufacturer()
    }

    fun delete(id: Int) = transaction {
        ManufacturerEntity[id].deletedAt = LocalDateTime.now()
    }
}