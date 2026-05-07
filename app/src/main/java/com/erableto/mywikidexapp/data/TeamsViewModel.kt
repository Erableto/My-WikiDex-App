package com.erableto.mywikidexapp.data

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.erableto.mywikidexapp.model.PKMN
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TeamsViewModel(context: Context): ViewModel() {
    private val dao = DB.getDB(context).teamsDAO()
    private val _searchQuery = MutableStateFlow<String>("")
    val searchQuery = _searchQuery.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val teams = _searchQuery.flatMapLatest { query ->
        if (query.isEmpty()) {
            dao.getAll()
        } else {
            dao.searchTeams("%$query%")
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    val teamsPaged = _searchQuery.flatMapLatest { _ ->
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                dao.searchTeamsPaged("%${searchQuery.value}%")
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

    fun insert(
        name: String,
        pkmn1: PKMN?,
        pkmn2: PKMN?,
        pkmn3: PKMN?,
        pkmn4: PKMN?,
        pkmn5: PKMN?,
        pkmn6: PKMN?
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insert(
                Team(
                    name = name,
                    pkmn1 = pkmn1,
                    pkmn2 = pkmn2,
                    pkmn3 = pkmn3,
                    pkmn4 = pkmn4,
                    pkmn5 = pkmn5,
                    pkmn6 = pkmn6
                ) // El ID se autogenera.
            )
        }
    }

    fun delete(team: Team) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.delete(team)
        }
    }

    fun update(team: Team) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(team)
        }
    }
}