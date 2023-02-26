package com.example.plugins

import com.example.development.*
import com.example.model.*
import com.example.services.*
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {

    val useMariaDB: Boolean = (System.getenv("useMariaDB") ?: "false").toBoolean()

    val dbUser: String = System.getenv("dbUser") ?: "root"
    val dbPW: String = System.getenv("dbPW") ?: ""

    var dbUrl: String = System.getenv("dbUrl") ?: "jdbc:sqlite:TestDB"
    var dbDriver = "org.sqlite.JDBC"

    if (useMariaDB) {
        dbUrl = "jdbc:mysql:$dbUrl"
        dbDriver = "com.mysql.cj.jdbc.Driver"
    }

    val database = Database.connect(
        url = dbUrl, user = dbUser, driver = dbDriver, password = dbPW
    )

    transaction {
        SchemaUtils.drop(
            Users, Trays, Shelfs, ShelfTrays, PartTypes, Manufacturers, Parts
        )
    }
    transaction {
        SchemaUtils.create(
            Users, Trays, Shelfs, ShelfTrays, PartTypes, Manufacturers, Parts
        )
    }



    transaction {
        for (user in sampleUsers) {
            UserService().add(user, "TBD")
        }
    }
    transaction {
        for (tray in sampleTrays) {
            TrayService().add(tray, "TBD")
        }
    }
    transaction {
        for (shelf in sampleShelfs) {
            ShelfService().add(shelf, "TBD")
        }
    }
    transaction {
        for (manufacturer in sampleManufacturers) {
            ManufacturerService().add(manufacturer, "TBD")
        }
    }
    transaction {
        for (partType in samplePartTypes) {
            PartTypeService().add(partType, "TBD")
        }
    }
    transaction {
        for (part in sampleParts) {
            PartService().add(part, "TBD")
        }
    }
}