package at.fklab.model

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime


object Users : IntIdTable("Users") {
    val name = varchar("name", 100)
    val lastLogin = datetime("lastLogin")

    val updatedAt = datetime("updatedAt").nullable()
    val updatedBy = varchar("updatedBy",100).nullable()
    val deletedAt = datetime("deletedAt").nullable()
    val deletedBy = varchar("deletedBy",100).nullable()
}

class UserEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserEntity>(Users)

    var name by Users.name
    var lastLogin by Users.lastLogin

    var updatedAt by Users.updatedAt
    var updatedBy by Users.updatedBy
    var deletedAt by Users.deletedAt
    var deletedBy by Users.deletedBy

    fun toUser() = User(
        id.value, name, lastLogin
    )
}

class User(
    var id: Int?,
    var name: String,
    var lastLogin: LocalDateTime
)