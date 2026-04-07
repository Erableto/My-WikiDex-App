package com.erableto.mywikidexapp.data

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritesDAO {
    @Query("SELECT * FROM favorites ORDER BY id DESC")
    fun getAll(): Flow<List<Favorite>>

    @Query("SELECT * FROM favorites ORDER BY id DESC")
    fun getAllPaged(): PagingSource<Int, Favorite>

    @Query("SELECT COUNT(*) FROM favorites")
    fun getCount(): Flow<Int>

    @Query("SELECT * FROM favorites WHERE url = :url")
    fun getByURL(url: String): Flow<Favorite>

    @Query("SELECT * FROM favorites WHERE title LIKE :query ORDER BY title DESC")
    fun searchFavorites(query: String): Flow<List<Favorite>>

    @Query("SELECT * FROM favorites WHERE title LIKE :query ORDER BY title DESC")
    fun searchFavoritesPaged(query: String): PagingSource<Int, Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: Favorite)

    @Delete
    suspend fun delete(favorite: Favorite)

    @Query("DELETE FROM favorites WHERE url = :url")
    suspend fun deleteByURL(url: String)

    @Query("DELETE FROM favorites")
    suspend fun deleteAll()

    @Update
    suspend fun update(favorite: Favorite)
}