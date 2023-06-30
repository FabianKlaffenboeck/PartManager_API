package at.fklab.services

import at.fklab.model.*
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class PartTypeService {

    fun getAll(): List<PartType> = transaction {
        val query = Op.build { PartTypes.deletedAt.isNull() }
        PartTypeEntity.find(query).map(PartTypeEntity::toPartType)
    }

    fun getById(id: Int): PartType? = transaction {
        PartTypeEntity.find {
            PartTypes.id eq id
        }.firstOrNull()?.toPartType()
    }

    fun getUnits(): List<MeasurementUnit> = MeasurementUnit.values().asList()

    fun getFootprints(): List<Footprint> = Footprint.values().asList()

    fun add(partType: PartType): PartType = transaction {
        PartTypeEntity.new {
            name = partType.name

            updatedAt = LocalDateTime.now()
        }.toPartType()
    }

    fun update(partType: PartType): PartType = transaction {
        val notNullId = partType.id ?: -1

        PartTypeEntity[notNullId].name = partType.name

        PartTypeEntity[notNullId].updatedAt = LocalDateTime.now()
        PartTypeEntity[notNullId].toPartType()
    }

    fun delite(id: Int) = transaction {
        PartTypeEntity[id].deletedAt = LocalDateTime.now()
    }
}