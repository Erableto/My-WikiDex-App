package com.example.mywikidexapp.data

import android.content.Context
import androidx.lifecycle.ViewModel

class HistoryViewModel(context: Context): ViewModel() {
    private val dao = DB.getDB(context).historyDAO()
}