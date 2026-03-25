package com.example.mywikidexapp.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mywikidexapp.ui.theme.MyWikiDexAppTheme

@Composable
fun HistoryScreenComposable() {
    Text("HISTORY")
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenComposablePreview() {
    MyWikiDexAppTheme() {
        HistoryScreenComposable()
    }
}