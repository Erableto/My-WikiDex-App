package com.example.mywikidexapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDAO {
    @Query("SELECT * FROM history ORDER BY timeMillis DESC")
    fun getEntries(): Flow<List<HistoryEntry>>

    @Query("SELECT * FROM history WHERE url = :url")
    fun getEntryByURL(url: String): Flow<HistoryEntry>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: HistoryEntry)

    @Delete
    suspend fun deleteEntry(entry: HistoryEntry)

    @Query("DELETE FROM history")
    suspend fun deleteAll()

    @Update
    suspend fun updateEntry(entry: HistoryEntry)
}