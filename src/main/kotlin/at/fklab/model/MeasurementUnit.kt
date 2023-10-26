package at.fklab.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime


object MeasurementUnits : IntIdTable("MeasurementUnits") {
    val name = varchar("name", 100)

    val updatedAt = datetime("updatedAt").nullable()
    val deletedAt = datetime("deletedAt").nullable()
}

class MeasurementUnitEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MeasurementUnitEntity>(MeasurementUnits)

    var name by MeasurementUnits.name

    var updatedAt by MeasurementUnits.updatedAt
    var deletedAt by MeasurementUnits.deletedAt

    fun toMeasurementUnit() = MeasurementUnit(
        id.value, name
    )
}

class MeasurementUnit(
    var id: Int?,
    var name: String
)