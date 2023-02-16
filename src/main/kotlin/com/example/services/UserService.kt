package com.example.services

import com.example.model.User
import com.example.model.UserEntity
import com.example.model.Users
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.transactions.transaction

class UserService {

    fun getAll(): List<User> = transaction {
        val query = Op.build { Users.id.isNotNull() }
        UserEntity.find(query).map(UserEntity::toUser)
    }

    fun getById(id: Int): User? = transaction {
        UserEntity.find {
            Users.id eq id
        }.firstOrNull()?.toUser()
    }

    fun add(user: User): User = transaction {
        UserEntity.new {
            name = user.name
            lastLogin = user.lastLogin
        }.toUser()
    }

    fun update(user: User): User = transaction {
        val notNullId = user.id ?: -1

        UserEntity[notNullId].name = user.name
        UserEntity[notNullId].lastLogin = user.lastLogin
        UserEntity[notNullId].toUser()
    }
}