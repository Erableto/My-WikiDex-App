package com.example.mywikidexapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Favorite::class, HistoryEntry::class], version = 3)
abstract class DB: RoomDatabase() {
    abstract fun favoritesDAO(): FavoritesDAO
    abstract fun historyDAO(): HistoryDAO

    companion object {
        @Volatile
        private var INSTANCE: DB ?= null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "DELETE FROM history"
                )
                db.execSQL(
                    "CREATE UNIQUE INDEX IF NOT EXISTS index_history_url ON history(url)"
                )
            }
        }
        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "DELETE FROM history"
                )
                db.execSQL(
                    "DELETE FROM favorites"
                )
                db.execSQL(
                    "CREATE UNIQUE INDEX IF NOT EXISTS index_history_title ON history(title)"
                )
                db.execSQL(
                    "CREATE UNIQUE INDEX IF NOT EXISTS index_favorites_url ON favorites(url)"
                )
                db.execSQL(
                    "CREATE UNIQUE INDEX IF NOT EXISTS index_favorites_title ON favorites(title)"
                )
            }
        }

        fun getDB(context: Context): DB {
            return INSTANCE?: synchronized(this) /* Para que no se cree más de una BDD. */ {
                Room.databaseBuilder(
                    context.applicationContext,
                    DB::class.java,
                    "wikidex_app_db"
                ).addMigrations(MIGRATION_1_2, MIGRATION_2_3).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}