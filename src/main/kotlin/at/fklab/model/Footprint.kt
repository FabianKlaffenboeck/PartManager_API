package at.fklab.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime


object Footprints : IntIdTable("Footprints") {
    val name = varchar("name", 100)

    val updatedAt = datetime("updatedAt").nullable()
    val deletedAt = datetime("deletedAt").nullable()
}

class FootprintEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<FootprintEntity>(Footprints)

    var name by Footprints.name

    var updatedAt by Footprints.updatedAt
    var deletedAt by Footprints.deletedAt

    fun toFootprint() = Footprint(
        id.value, name
    )
}

class Footprint(
    var id: Int?,
    var name: String
)