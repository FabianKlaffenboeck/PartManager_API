package at.fklab.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime


object Manufacturers : IntIdTable("Manufacturers") {
    val name = varchar("name", 100)

    val updatedAt = datetime("updatedAt").nullable()
    val updatedBy = varchar("updatedBy",100).nullable()
    val deletedAt = datetime("deletedAt").nullable()
    val deletedBy = varchar("deletedBy",100).nullable()
}

class ManufacturerEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ManufacturerEntity>(Manufacturers)

    var name by Manufacturers.name

    var updatedAt by Manufacturers.updatedAt
    var updatedBy by Manufacturers.updatedBy
    var deletedAt by Manufacturers.deletedAt
    var deletedBy by Manufacturers.deletedBy

    fun toManufacturer() = Manufacturer(
        id.value, name
    )
}

class Manufacturer(
    var id: Int?,
    var name: String
)