package at.fklab.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime


object Parts : IntIdTable("Parts") {

    val name = varchar("name", 100)
    val quantity = integer("quantity")
    val measurementUnit = enumeration("measurementUnit", MeasurementUnit::class).nullable()
    val value = double("value").nullable()
    val footprint = enumeration("footprint", Footprint::class).nullable()

    val partType_id = reference("partType_id", PartTypes)
    val manufacturer_id = reference("manufacturer_id", Manufacturers)
    val tray_id = reference("tray_id", Trays)

    val updatedAt = datetime("updatedAt").nullable()
    val updatedBy = varchar("updatedBy", 100).nullable()
    val deletedAt = datetime("deletedAt").nullable()
    val deletedBy = varchar("deletedBy", 100).nullable()
}

class PartEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PartEntity>(Parts)

    var name by Parts.name
    var quantity by Parts.quantity
    var measurementUnit by Parts.measurementUnit
    var value by Parts.value
    var footprint by Parts.footprint

    var partType by PartTypeEntity referencedOn Parts.partType_id
    var manufacturer by ManufacturerEntity referencedOn Parts.manufacturer_id
    var tray by TrayEntity referencedOn Parts.tray_id

    var updatedAt by Parts.updatedAt
    var updatedBy by Parts.updatedBy
    var deletedAt by Parts.deletedAt
    var deletedBy by Parts.deletedBy

    fun toPart() = Part(
        id.value,
        name,
        quantity,
        measurementUnit,
        value,
        footprint,
        partType.toPartType(),
        manufacturer.toManufacturer(),
        tray.toTray(),
    )
}

class Part(
    var id: Int?,
    var name: String,
    var quantity: Int,
    var measurementUnit: MeasurementUnit?,
    var value: Double?,
    var footprint: Footprint?,
    var partType: PartType,
    var manufacturer: Manufacturer,
    var tray: Tray,
)