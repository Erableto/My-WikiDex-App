package com.example.mywikidexapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class, HistoryEntry::class], version = 1)
abstract class DB: RoomDatabase() {
    abstract fun favoritesDAO(): FavoritesDAO
    abstract fun historyDAO(): HistoryDAO

    companion object {
        @Volatile
        private var INSTANCE: DB ?= null

        fun getDB(context: Context): DB {
            return INSTANCE?: synchronized(this) /* Para que no se cree más de una BDD. */ {
                Room.databaseBuilder(
                    context.applicationContext,
                    DB::class.java,
                    "wikidex_app_db"
                ).build().also {
                    INSTANCE = it
                }
            }
        }
    }
}