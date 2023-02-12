package com.example.plugins

import com.example.model.*
import com.example.services.UserService
import development.sampleUsers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import io.ktor.server.application.*

fun Application.configureDatabases() {
    val database = Database.connect(
        url = "jdbc:sqlite:TestDB", user = "root", driver = "org.sqlite.JDBC", password = ""
    )
    transaction {
        SchemaUtils.drop(
            Users,Trays,Shelfs,PartTypes,Manufacturers,Parts
        )
    }
    transaction {
        SchemaUtils.create(
            Users,Trays,Shelfs,PartTypes,Manufacturers,Parts
        )
    }

    transaction {
        for (user in sampleUsers){
            UserService().add(user)
        }
    }
}