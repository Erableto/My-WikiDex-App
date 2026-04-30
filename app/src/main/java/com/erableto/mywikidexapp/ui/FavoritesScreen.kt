package com.erableto.mywikidexapp.ui

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.data.Favorite
import com.erableto.mywikidexapp.data.FavoritesViewModel
import com.erableto.mywikidexapp.data.FavoritesViewModelFactory
import com.erableto.mywikidexapp.ui.components.FavoritesListItem
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme

val favoritesList_ = mutableStateListOf(
    Favorite(
        0,
        "https://www.wikidex.net/wiki/Usuario:Erableto",
        "Usuario:Erableto"
    ),
    Favorite(
        1,
        "https://www.wikidex.net/wiki/Usuario:Erableto/Tests_2",
        "Usuario:Erableto/Tests 2"
    ),
    Favorite(
        2,
        "https://www.wikidex.net/wiki/Usuario:Zer%C3%B8/Portada",
        "Usuario:Zerø/Portada"
    )
)

val favoritesList_empty = mutableStateListOf<Favorite>()

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = ViewModelProvider(
        LocalActivity.current as ComponentActivity,
        FavoritesViewModelFactory(LocalContext.current)
    )[FavoritesViewModel::class.java], // ).get(FavoritesViewModel::class.java),
    onNavigateToWiki: (String) -> Unit
) {
    val context = LocalContext.current

    //val favoritesList by viewModel.favorites.collectAsState()
    val favoritesCount by viewModel.count.collectAsState()
    val favoritesListPaged = viewModel.favoritesPaged.collectAsLazyPagingItems()
    val searchQuery by viewModel.searchQuery.collectAsState()

    val keyboardController = LocalSoftwareKeyboardController.current

    fun cancelSearch() {
        viewModel.onSearchQueryChanged("")
        keyboardController?.hide() // Ocultamos el teclado manualmente.
    }

    // Botón atrás para cancelar la búsqueda.
    BackHandler {
        if (searchQuery.isNotEmpty()) {
            cancelSearch()
        } else {
            // Si no puede ir atrás, dejamos que Android cierre la pantalla.
            (context as? Activity)?.finish()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (favoritesCount == 0) {
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
                    painter = painterResource(R.drawable.rounded_heart_broken_250),
                    contentDescription = "No hay favoritos",
                    colorFilter = if (isSystemInDarkTheme()) {
                        ColorFilter.tint(Color.White)
                    } else {
                        ColorFilter.tint(Color.Black)
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "No hay favoritos",
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp
                )
            }
        } else {
            Column {
                TextField(
                    value = searchQuery ?: "",
                    onValueChange = {
                        viewModel.onSearchQueryChanged(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    isError = favoritesListPaged.itemCount == 0 && searchQuery.isNotEmpty(),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            keyboardController?.hide() // Ocultamos el teclado manualmente.
                        }
                    ),
                    placeholder = {
                        Text("Buscar favoritos")
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.rounded_search_24),
                            contentDescription = "Buscar"
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(
                                onClick = {
                                    cancelSearch()
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.rounded_close_24),
                                    contentDescription = "Cerrar"
                                )
                            }
                        }
                    }
                )

                if (favoritesListPaged.itemCount == 0 && searchQuery.isNotEmpty()) {
                    Text(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        text = "No se han encontrado resultados para \"$searchQuery\".",
                        textAlign = TextAlign.Center,
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 8.dp, end = 8.dp)
                    ) {
                        items(
                            count = favoritesListPaged.itemCount,
                            key = { index ->
                                val item = favoritesListPaged[index]
                                item?.id ?: index
                            }
                        ) { index ->
                            val favorite = favoritesListPaged[index]
                            if (favorite != null) {
                                FavoritesListItem(
                                    favorite = favorite,
                                    onClickFav = {
                                        onNavigateToWiki(favorite.url)
                                    },
                                    onClickDeleteFav = {
                                        //favoritesList.remove(favorite)
                                        viewModel.delete(favorite)
                                    }
                                )
                            }
                        }

                        if (favoritesListPaged.loadState.append is LoadState.Loading) {
                            item {
                                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenPreview() {
    var DUMMY: String

    MyWikiDexAppTheme() {
        FavoritesScreen(onNavigateToWiki = { url ->
            DUMMY = url
        })
    }
}