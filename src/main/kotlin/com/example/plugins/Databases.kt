package com.example.plugins

import com.example.development.*
import com.example.model.*
import com.example.services.*
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases(dbUrl: String, dbUser: String, dbPW: String, initDB: Boolean, populateDB: Boolean) {

    val database = Database.connect(
        url = dbUrl, user = dbUser, password = dbPW
    )

    if (initDB) {

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

        if (populateDB) {
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

    }
}