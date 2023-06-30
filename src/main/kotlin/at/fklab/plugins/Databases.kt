package at.fklab.plugins

import at.fklab.development.*
import at.fklab.model.*
import at.fklab.services.*
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
                Trays, Shelfs, ShelfTrays, PartTypes, Manufacturers, Parts
            )
        }
        transaction {
            SchemaUtils.create(
                Trays, Shelfs, ShelfTrays, PartTypes, Manufacturers, Parts
            )
        }

        if (populateDB) {
            transaction {
                for (tray in sampleTrays) {
                    TrayService().add(tray)
                }
            }
            transaction {
                for (shelf in sampleShelfs) {
                    ShelfService().add(shelf)
                }
            }
            transaction {
                for (manufacturer in sampleManufacturers) {
                    ManufacturerService().add(manufacturer)
                }
            }
            transaction {
                for (partType in samplePartTypes) {
                    PartTypeService().add(partType)
                }
            }
            transaction {
                for (part in sampleParts) {
                    PartService().add(part)
                }
            }
        }
    }
}