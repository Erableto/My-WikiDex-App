package com.example.mywikidexapp.ui.components

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
import com.example.mywikidexapp.R
import com.example.mywikidexapp.data.HistoryEntry
import com.example.mywikidexapp.ui.theme.MyWikiDexAppTheme
import java.text.DateFormat
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Text(dateString)

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.clickable {
                onClickEntry()
            }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    historyEntry.title,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                FloatingActionButton(onClick = onClickDeleteEntry) {
                    Icon(
                        painter = painterResource(R.drawable.rounded_delete_24),
                        contentDescription = "Borrar entrada del historial"
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryListItemPreview() {
    val historyEntry = HistoryEntry(0, 1774457631000L, "", "Hola mundo")

    MyWikiDexAppTheme() {
        HistoryListItem(
            historyEntry,
            {},
            {}
        )
    }
}