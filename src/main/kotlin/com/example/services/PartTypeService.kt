package com.example.services

import com.example.model.*
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

    fun add(partType: PartType, user: String): PartType = transaction {
        PartTypeEntity.new {
            name = partType.name

            updatedAt = LocalDateTime.now()
            updatedBy = user
        }.toPartType()
    }

    fun update(partType: PartType, user: String): PartType = transaction {
        val notNullId = partType.id ?: -1

        PartTypeEntity[notNullId].name = partType.name

        PartTypeEntity[notNullId].updatedAt = LocalDateTime.now()
        PartTypeEntity[notNullId].updatedBy = user
        PartTypeEntity[notNullId].toPartType()
    }

    fun delite(id: Int, user: String) = transaction {
        PartTypeEntity[id].deletedAt = LocalDateTime.now()
        PartTypeEntity[id].deletedBy = user
    }
}