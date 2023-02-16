package com.example.services

import com.example.model.Shelf
import com.example.model.ShelfEntity
import com.example.model.Shelfs
import com.example.model.TrayEntity
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.SizedCollection
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class ShelfService {

    fun getAll(): List<Shelf> = transaction {
        val query = Op.build { Shelfs.id.isNotNull() }
        ShelfEntity.find(query).map(ShelfEntity::toShelf)
    }

    fun getById(id: Int): Shelf? = transaction {
        ShelfEntity.find {
            Shelfs.id eq id
        }.firstOrNull()?.toShelf()
    }

    fun add(shelf: Shelf, user: String): Shelf = transaction {
        ShelfEntity.new {
            name = shelf.name
            trays = SizedCollection(shelf.trays.map {
                TrayEntity.findById(it.id ?: 0)!!
            })

            updatedAt = LocalDateTime.now()
            updatedBy = user
        }.toShelf()
    }

    fun update(shelf: Shelf, user: String): Shelf = transaction {
        val notNullId = shelf.id ?: -1

        ShelfEntity[notNullId].name = shelf.name
        ShelfEntity[notNullId].trays = SizedCollection(shelf.trays.map {
            TrayEntity.findById(it.id ?: 0)!!
        })

        ShelfEntity[notNullId].updatedAt = LocalDateTime.now()
        ShelfEntity[notNullId].updatedBy = user
        ShelfEntity[notNullId].toShelf()
    }

    fun delite(id: Int, user: String) = transaction {
        ShelfEntity[id].deletedAt = LocalDateTime.now()
        ShelfEntity[id].deletedBy = user
    }
}