package com.example.mywikidexapp.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(context: Context): ViewModel() {
    private val dao = DB.getDB(context).favoritesDAO()

    val favorites = dao.getAll()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    fun insert(url: String, title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(Favorite(url = url, title = title)) // El ID se autogenera.
        }
    }

    fun delete(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(favorite)
        }
    }
}