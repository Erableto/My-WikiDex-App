package com.example.mywikidexapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mywikidexapp.AppDestinations
import com.example.mywikidexapp.data.Favorite
import com.example.mywikidexapp.ui.components.FavoritesListItem
import com.example.mywikidexapp.ui.theme.MyWikiDexAppTheme

val favoritesList = mutableStateListOf(
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

@Composable
fun FavoritesScreenComposable(onNavigateToWiki: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(favoritesList.size) { index: Int ->
            val favorite = favoritesList[index]

            FavoritesListItem(
                favorite = favorite,
                onClickFav = {
                    onNavigateToWiki(favorite.url)
                },
                onClickDeleteFav = {
                    favoritesList.remove(favorite)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritesScreenComposablePreview() {
    var DUMMY: String

    MyWikiDexAppTheme() {
        FavoritesScreenComposable(onNavigateToWiki = { url ->
            DUMMY = url
        })
    }
}