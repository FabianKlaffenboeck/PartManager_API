package at.fklab.services

import at.fklab.model.*
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class FootprintService {

    fun getAll(): List<Footprint> = transaction {
        val query = Op.build { Footprints.deletedAt.isNull() }
        FootprintEntity.find(query).map(FootprintEntity::toFootprint)
    }

    fun getById(id: Int): Footprint? = transaction {
        FootprintEntity.find {
            Footprints.id eq id
        }.firstOrNull()?.toFootprint()
    }

    fun add(footprint: Footprint): Footprint = transaction {
        FootprintEntity.new {
            name = footprint.name

            updatedAt = LocalDateTime.now()
        }.toFootprint()
    }

    fun update(footprint: Footprint): Footprint = transaction {
        val notNullId = footprint.id ?: -1

        FootprintEntity[notNullId].name = footprint.name

        FootprintEntity[notNullId].updatedAt = LocalDateTime.now()
        FootprintEntity[notNullId].toFootprint()
    }

    fun delete(id: Int) = transaction {
        FootprintEntity[id].deletedAt = LocalDateTime.now()
    }
}