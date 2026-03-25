package com.example.mywikidexapp.data

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
    fun getFavorites(): Flow<List<Favorite>>

    @Query("SELECT * FROM favorites WHERE url = :url")
    fun getFavoriteByURL(url: String): Flow<Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    @Query("DELETE FROM favorites WHERE url = :url")
    suspend fun deleteFavoriteByURL(url: String)

    @Query("DELETE FROM favorites")
    suspend fun deleteAll()

    @Update
    suspend fun updateFavorite(favorite: Favorite)
}