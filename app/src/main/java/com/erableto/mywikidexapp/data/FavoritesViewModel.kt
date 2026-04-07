package com.erableto.mywikidexapp.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(context: Context): ViewModel() {
    private val dao = DB.getDB(context).favoritesDAO()
    private val _searchQuery = MutableStateFlow<String>("")
    val searchQuery = _searchQuery.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val favorites = _searchQuery.flatMapLatest { query ->
        if (query.isEmpty()) {
            dao.getAll()
        } else {
            dao.searchFavorites("%$query%")
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val favoritesPaged = _searchQuery.flatMapLatest { query ->
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                dao.searchFavoritesPaged("%${searchQuery.value}%")
            }
        ).flow
    }.cachedIn(viewModelScope)

    fun onSearchQueryChanged(newQuery: String) {
        _searchQuery.value = newQuery
    }

    val count = dao.getCount()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            0
        )

    fun insert(url: String, title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(
                Favorite(
                    url = url,
                    title = title
                ) // El ID se autogenera.
            )
        }
    }

    fun delete(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(favorite)
        }
    }

    fun update(favorite: Favorite) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(favorite)
        }
    }
}