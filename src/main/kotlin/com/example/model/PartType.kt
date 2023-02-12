package com.example.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime


object PartTypes : IntIdTable("PartType") {
    val type = varchar("type", 100)

    val updatedAt = datetime("updatedAt").nullable()
    val updatedBy = reference("updatedBy", Users).nullable()
    val deletedAt = datetime("deletedAt").nullable()
    val deletedBy = reference("deletedBy", Users).nullable()
}

class PartTypeEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PartTypeEntity>(PartTypes)

    var type by PartTypes.type

    var updatedAt by PartTypes.updatedAt
    var updatedBy by PartTypes.updatedBy
    var deletedAt by PartTypes.deletedAt
    var deletedBy by PartTypes.deletedBy

    fun toPartType() = PartType(
        id.value, type
    )
}

class PartType(
    var id: Int?,
    var type: String
)