package at.fklab.services

import at.fklab.model.Tray
import at.fklab.model.TrayEntity
import at.fklab.model.Trays
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class TrayService {

    fun getAll(): List<Tray> = transaction {
        val query = Op.build { Trays.deletedAt.isNull() }
        TrayEntity.find(query).map(TrayEntity::toTray)
    }

    fun getById(id: Int): Tray? = transaction {
        TrayEntity.find {
            Trays.id eq id
        }.firstOrNull()?.toTray()
    }

    fun add(tray: Tray, user: String): Tray = transaction {
        TrayEntity.new {
            name = tray.name

            updatedAt = LocalDateTime.now()
            updatedBy = user
        }.toTray()
    }

    fun update(tray: Tray, user: String): Tray = transaction {
        val notNullId = tray.id ?: -1

        TrayEntity[notNullId].name = tray.name

        TrayEntity[notNullId].updatedAt = LocalDateTime.now()
        TrayEntity[notNullId].updatedBy = user
        TrayEntity[notNullId].toTray()
    }

    fun delite(id: Int, user: String) = transaction {
        TrayEntity[id].deletedAt = LocalDateTime.now()
        TrayEntity[id].deletedBy = user
    }
}