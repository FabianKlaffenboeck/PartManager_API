package at.eWolveLabs.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime


object PartTypes : IntIdTable("PartTypes") {
    val name = varchar("name", 100)

    val updatedAt = datetime("updatedAt").nullable()
    val deletedAt = datetime("deletedAt").nullable()
}

class PartTypeEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PartTypeEntity>(PartTypes)

    var name by PartTypes.name

    var updatedAt by PartTypes.updatedAt
    var deletedAt by PartTypes.deletedAt

    fun toPartType() = PartType(
        id.value,
        name,
    )
}

data class PartType(
    var id: Int?,
    var name: String,
)