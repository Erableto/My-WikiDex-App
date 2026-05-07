package com.erableto.mywikidexapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Favorite::class, HistoryEntry::class, Team::class], version = 4)
abstract class DB: RoomDatabase() {
    abstract fun favoritesDAO(): FavoritesDAO
    abstract fun historyDAO(): HistoryDAO
    abstract fun teamsDAO(): TeamsDAO

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

        private val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    """
                    CREATE TABLE IF NOT EXISTS `teams` (
                        `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 
                        `name` TEXT NOT NULL, 
                        `pkmn1_pkmnName` TEXT, `pkmn1_itemName` TEXT, `pkmn1_pkmnIcon` TEXT, `pkmn1_itemIcon` TEXT, `pkmn1_type1` TEXT, `pkmn1_type2` TEXT, `pkmn1_gender` TEXT, `pkmn1_lv` INTEGER, `pkmn1_ability` TEXT, `pkmn1_mov1` TEXT, `pkmn1_mov2` TEXT, `pkmn1_mov3` TEXT, `pkmn1_mov4` TEXT, 
                        `pkmn2_pkmnName` TEXT, `pkmn2_itemName` TEXT, `pkmn2_pkmnIcon` TEXT, `pkmn2_itemIcon` TEXT, `pkmn2_type1` TEXT, `pkmn2_type2` TEXT, `pkmn2_gender` TEXT, `pkmn2_lv` INTEGER, `pkmn2_ability` TEXT, `pkmn2_mov1` TEXT, `pkmn2_mov2` TEXT, `pkmn2_mov3` TEXT, `pkmn2_mov4` TEXT, 
                        `pkmn3_pkmnName` TEXT, `pkmn3_itemName` TEXT, `pkmn3_pkmnIcon` TEXT, `pkmn3_itemIcon` TEXT, `pkmn3_type1` TEXT, `pkmn3_type2` TEXT, `pkmn3_gender` TEXT, `pkmn3_lv` INTEGER, `pkmn3_ability` TEXT, `pkmn3_mov1` TEXT, `pkmn3_mov2` TEXT, `pkmn3_mov3` TEXT, `pkmn3_mov4` TEXT, 
                        `pkmn4_pkmnName` TEXT, `pkmn4_itemName` TEXT, `pkmn4_pkmnIcon` TEXT, `pkmn4_itemIcon` TEXT, `pkmn4_type1` TEXT, `pkmn4_type2` TEXT, `pkmn4_gender` TEXT, `pkmn4_lv` INTEGER, `pkmn4_ability` TEXT, `pkmn4_mov1` TEXT, `pkmn4_mov2` TEXT, `pkmn4_mov3` TEXT, `pkmn4_mov4` TEXT, 
                        `pkmn5_pkmnName` TEXT, `pkmn5_itemName` TEXT, `pkmn5_pkmnIcon` TEXT, `pkmn5_itemIcon` TEXT, `pkmn5_type1` TEXT, `pkmn5_type2` TEXT, `pkmn5_gender` TEXT, `pkmn5_lv` INTEGER, `pkmn5_ability` TEXT, `pkmn5_mov1` TEXT, `pkmn5_mov2` TEXT, `pkmn5_mov3` TEXT, `pkmn5_mov4` TEXT, 
                        `pkmn6_pkmnName` TEXT, `pkmn6_itemName` TEXT, `pkmn6_pkmnIcon` TEXT, `pkmn6_itemIcon` TEXT, `pkmn6_type1` TEXT, `pkmn6_type2` TEXT, `pkmn6_gender` TEXT, `pkmn6_lv` INTEGER, `pkmn6_ability` TEXT, `pkmn6_mov1` TEXT, `pkmn6_mov2` TEXT, `pkmn6_mov3` TEXT, `pkmn6_mov4` TEXT
                    )
                    """.trimIndent()
                )
            }
        }

        fun getDB(context: Context): DB {
            return INSTANCE?: synchronized(this) /* Para que no se cree más de una BDD. */ {
                Room.databaseBuilder(
                    context.applicationContext,
                    DB::class.java,
                    "wikidex_app_db"
                ).addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}