package com.example.services

import com.example.model.Tray
import com.example.model.TrayEntity
import com.example.model.Trays
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class TrayService {

    fun getAll(): List<Tray> = transaction {
        val query = Op.build { Trays.id.isNotNull() }
        TrayEntity.find(query).map(TrayEntity::toTray)
    }

    fun getById(id: Int): Tray? = transaction {
        TrayEntity.find {
            Trays.id eq id
        }.firstOrNull()?.toTray()
    }

    fun add(tray: Tray, user: String): Tray = transaction {
        TrayEntity.new {
            label = tray.label

            updatedAt = LocalDateTime.now()
            updatedBy = user
        }.toTray()
    }

    fun update(tray: Tray, user: String): Tray = transaction {
        val notNullId = tray.id ?: -1
        TrayEntity[notNullId].label = tray.label

        TrayEntity[notNullId].updatedAt = LocalDateTime.now()
        TrayEntity[notNullId].updatedBy = user
        TrayEntity[notNullId].toTray()
    }

    fun delite(id: Int, user: String) = transaction {
        TrayEntity[id].deletedAt = LocalDateTime.now()
        TrayEntity[id].deletedBy = user
    }
}