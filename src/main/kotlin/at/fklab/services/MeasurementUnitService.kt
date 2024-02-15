package at.fklab.services

import at.fklab.model.MeasurementUnit
import at.fklab.model.MeasurementUnitEntity
import at.fklab.model.MeasurementUnits
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class MeasurementUnitService {

    fun getAll(): List<MeasurementUnit> = transaction {
        val query = Op.build { MeasurementUnits.deletedAt.isNull() }
        MeasurementUnitEntity.find(query).map(MeasurementUnitEntity::toMeasurementUnit)
    }

    fun getById(id: Int): MeasurementUnit? = transaction {
        MeasurementUnitEntity.find {
            MeasurementUnits.id eq id
        }.firstOrNull()?.toMeasurementUnit()
    }

    fun add(measurementUnit: MeasurementUnit): MeasurementUnit = transaction {
        MeasurementUnitEntity.new {
            name = measurementUnit.name

            updatedAt = LocalDateTime.now()
        }.toMeasurementUnit()
    }

    fun update(measurementUnit: MeasurementUnit): MeasurementUnit = transaction {
        val notNullId = measurementUnit.id ?: -1

        MeasurementUnitEntity[notNullId].name = measurementUnit.name

        MeasurementUnitEntity[notNullId].updatedAt = LocalDateTime.now()
        MeasurementUnitEntity[notNullId].toMeasurementUnit()
    }

    fun delete(id: Int) = transaction {
        MeasurementUnitEntity[id].deletedAt = LocalDateTime.now()
    }
}