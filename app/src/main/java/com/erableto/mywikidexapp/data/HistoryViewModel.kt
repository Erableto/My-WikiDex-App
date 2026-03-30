package com.erableto.mywikidexapp.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HistoryViewModel(context: Context): ViewModel() {
    private val dao = DB.getDB(context).historyDAO()

    val history = dao.getAll()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
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