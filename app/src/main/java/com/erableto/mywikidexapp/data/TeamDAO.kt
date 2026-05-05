package com.erableto.mywikidexapp.data

import androidx.paging.PagingSource
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface TeamDAO {
    @Query("SELECT * FROM teams ORDER BY id DESC")
    fun getAll(): Flow<List<Team>>

    @Query("SELECT * FROM teams ORDER BY id DESC")
    fun getAllPaged(): PagingSource<Int, Team>

    @Query("SELECT COUNT(*) FROM teams")
    fun getCount(): Flow<Int>

    @Query("SELECT * FROM teams WHERE name LIKE :query ORDER BY name DESC")
    fun searchTeams(query: String): Flow<List<Team>>

    @Query("SELECT * FROM teams WHERE name LIKE :query ORDER BY name DESC")
    fun searchTeamsPaged(query: String): PagingSource<Int, Team>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(team: Team)

    @Delete
    suspend fun delete(team: Team)

    @Query("DELETE FROM teams")
    suspend fun deleteAll()

    @Update
    suspend fun update(team: Team)
}