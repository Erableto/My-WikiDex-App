package com.erableto.mywikidexapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.data.HistoryEntry
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun HistoryListItem(
    historyEntry: HistoryEntry,
    onClickEntry: () -> Unit,
    onClickDeleteEntry: () -> Unit
) {
    val timeMillis = historyEntry.timeMillis
    val date = Date(timeMillis)
    val dateFormat = SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy - HH:mm:ss")
    val dateString = dateFormat.format(date)

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(dateString)

        Spacer(modifier = Modifier.height(8.dp))

        CardFABItem(
            historyEntry.title,
            painterResource(R.drawable.rounded_delete_24),
            "Borrar entrada del historial",
            onClickEntry,
            onClickDeleteEntry
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryListItemPreview() {
    val historyEntry = HistoryEntry(0, "", "Hola mundo", 1774457631000L)

    MyWikiDexAppTheme() {
        HistoryListItem(
            historyEntry,
            {},
            {}
        )
    }
}