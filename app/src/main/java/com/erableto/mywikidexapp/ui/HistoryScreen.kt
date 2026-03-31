package com.erableto.mywikidexapp.ui

import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.data.HistoryEntry
import com.erableto.mywikidexapp.data.HistoryViewModel
import com.erableto.mywikidexapp.data.HistoryViewModelFactory
import com.erableto.mywikidexapp.ui.components.HistoryListItem
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme

val historyList_ = mutableStateListOf(
    HistoryEntry(
        0,
        "https://www.wikidex.net/wiki/Usuario:Erableto",
        "Usuario:Erableto",
        0L
    ),
    HistoryEntry(
        1,
        "https://www.wikidex.net/wiki/Usuario:Erableto/Tests_2",
        "Usuario:Erableto/Tests 2",
        0L
    ),
    HistoryEntry(
        2,
        "https://www.wikidex.net/wiki/Usuario:Zer%C3%B8/Portada",
        "Usuario:Zerø/Portada",
        0L
    )
)

val historyList_empty = mutableStateListOf<HistoryEntry>()

@Composable
fun HistoryScreenComposable(
    viewModel: HistoryViewModel = ViewModelProvider(
        LocalActivity.current as ComponentActivity,
        HistoryViewModelFactory(LocalContext.current)
    )[HistoryViewModel::class.java], // ).get(HistoryViewModel::class.java),
    onNavigateToWiki: (String) -> Unit
) {
    val historyList by viewModel.history.collectAsState()

    var showDialog by remember {
        mutableStateOf(false)
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = {
                Text("Borrar historial")
            },
            text = {
                Text("¿Quieres borrar el historial?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        // TODO
                        //historyList.clear()
                        viewModel.deleteAll()

                        showDialog = false
                    }
                ) {
                    Text("SÍ")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("NO")
                }
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (historyList.isEmpty()) {
            Column(
                modifier = Modifier.padding(8.dp).align(Alignment.Center),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        //.fillMaxWidth()
                        .width(250.dp)
                        .aspectRatio(1f),
                    painter = painterResource(R.drawable.rounded_history_toggle_off_250),
                    contentDescription = "No hay entradas en el historial",
                    colorFilter = if (isSystemInDarkTheme()) {
                        ColorFilter.tint(Color.White)
                    } else {
                        ColorFilter.tint(Color.Black)
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "No hay entradas en el historial",
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                items(historyList.size) { index: Int ->
                    val historyEntry = historyList[index]

                    HistoryListItem(
                        historyEntry = historyEntry,
                        onClickEntry = {
                            onNavigateToWiki(historyEntry.url)
                        },
                        onClickDeleteEntry = {
                            //historyList.remove(historyEntry)
                            viewModel.delete(historyEntry)
                        }
                    )
                }
            }

            FloatingActionButton(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                onClick = {
                    showDialog = true
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.rounded_delete_history_24),
                    contentDescription = "Borrar historial"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenComposablePreview() {
    var DUMMY: String

    MyWikiDexAppTheme() {
        HistoryScreenComposable(onNavigateToWiki = { url ->
            DUMMY = url
        })
    }
}