package com.example.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime


object Users : IntIdTable("user") {
    val name = varchar("name", 200)
    val lastLogin = datetime("lastLogin")
    val deletedAt = datetime("deletedAt").nullable()
}

class UserEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserEntity>(Users)

    var name by Users.name
    var lastLogin by Users.lastLogin
    var deletedAt by Users.deletedAt

    fun toUser() = User(
        id.value, name, lastLogin
    )
}

class User(
    var id: Int?, var name: String, var lastLogin: LocalDateTime
)