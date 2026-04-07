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

class HistoryViewModel(context: Context): ViewModel() {
    private val dao = DB.getDB(context).historyDAO()
    private val _searchQuery = MutableStateFlow<String>("")
    val searchQuery = _searchQuery.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val history = _searchQuery.flatMapLatest { query ->
        if (query.isEmpty()) {
            dao.getAll()
        } else {
            dao.searchHistory("%$query%")
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val historyPaged = _searchQuery.flatMapLatest { query ->
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                dao.searchHistoryPaged("%${searchQuery.value}%")
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

    fun getByURL(url: String) = dao.getByURL(url)
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )

    fun insert(url: String, title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(
                HistoryEntry(
                    url = url,
                    title = title,
                    timeMillis = System.currentTimeMillis()
                ) // El ID se autogenera.
            )
        }
    }

    fun delete(historyEntry: HistoryEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(historyEntry)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteAll()
        }
    }

    fun update(historyEntry: HistoryEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(historyEntry)
        }
    }

    fun updateTimeMillis(historyEntry: HistoryEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(
                historyEntry.copy(
                    timeMillis = System.currentTimeMillis()
                )
            )
        }
    }
}