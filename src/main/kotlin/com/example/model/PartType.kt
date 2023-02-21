package com.example.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime


enum class measurementUnit {
    Ohm, Farad, Henry
}

object PartTypes : IntIdTable("PartType") {
    val name = varchar("name", 100)
    val unit = enumeration("unit", measurementUnit::class).nullable()

    val updatedAt = datetime("updatedAt").nullable()
    val updatedBy = varchar("updatedBy", 100).nullable()
    val deletedAt = datetime("deletedAt").nullable()
    val deletedBy = varchar("deletedBy", 100).nullable()
}

class PartTypeEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PartTypeEntity>(PartTypes)

    var name by PartTypes.name
    var unit by PartTypes.unit

    var updatedAt by PartTypes.updatedAt
    var updatedBy by PartTypes.updatedBy
    var deletedAt by PartTypes.deletedAt
    var deletedBy by PartTypes.deletedBy

    fun toPartType() = PartType(
        id.value,
        name,
        unit
    )
}

class PartType(
    var id: Int?,
    var name: String,
    var unit: measurementUnit?
)