package com.erableto.mywikidexapp.data

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
    fun getAll(): Flow<List<HistoryEntry>>

    @Query("SELECT * FROM history WHERE url = :url LIMIT 1")
    fun getByURL(url: String): Flow<HistoryEntry?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: HistoryEntry)

    @Delete
    suspend fun delete(entry: HistoryEntry)

    @Query("DELETE FROM history")
    suspend fun deleteAll()

    @Update
    suspend fun update(entry: HistoryEntry)
}