package com.example.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime


object Trays : IntIdTable("Tray") {
    val label = varchar("label", 100)

    val updatedAt = datetime("updatedAt").nullable()
    val updatedBy = varchar("updatedBy",100).nullable()
    val deletedAt = datetime("deletedAt").nullable()
    val deletedBy = varchar("deletedBy",100).nullable()
}

class TrayEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TrayEntity>(Trays)

    var label by Trays.label

    var updatedAt by Trays.updatedAt
    var updatedBy by Trays.updatedBy
    var deletedAt by Trays.deletedAt
    var deletedBy by Trays.deletedBy

    fun toTray() = Tray(
        id.value, label
    )
}

class Tray(
    var id: Int?,
    var label: String
)