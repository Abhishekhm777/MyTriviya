package com.example.mytriviya.data.local

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

//Migration class
object DatabaseMigrations {
    const val DB_VERSION = 3

    val MIGRATIONS: Array<Migration>
        get() = arrayOf(
            migration12()
        )

    private fun migration12(): Migration = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE trivia ADD COLUMN body TEXT")
        }
    }
}
