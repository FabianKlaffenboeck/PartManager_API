package com.example.services

import com.example.model.User
import com.example.model.UserEntity
import com.example.model.Users
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class UserService {

    fun getAll(): List<User> = transaction {
        val query = Op.build { Users.deletedAt.isNull() }
        UserEntity.find(query).map(UserEntity::toUser)
    }

    fun getById(id: Int): User? = transaction {
        UserEntity.find {
            Users.id eq id
        }.firstOrNull()?.toUser()
    }

    fun add(user: User, userUpdater: String): User = transaction {
        UserEntity.new {
            name = user.name
            lastLogin = user.lastLogin

            updatedAt = LocalDateTime.now()
            updatedBy = userUpdater
        }.toUser()
    }

    fun update(user: User, userUpdater: String): User = transaction {
        val notNullId = user.id ?: -1

        UserEntity[notNullId].name = user.name
        UserEntity[notNullId].lastLogin = user.lastLogin

        UserEntity[notNullId].updatedAt = LocalDateTime.now()
        UserEntity[notNullId].updatedBy = userUpdater
        UserEntity[notNullId].toUser()
    }


    fun delite(id: Int, user: String) = transaction {
        UserEntity[id].deletedAt = LocalDateTime.now()
        UserEntity[id].deletedBy = user
    }
}