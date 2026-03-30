package com.example.mywikidexapp.ui

import androidx.activity.ComponentActivity
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import com.example.mywikidexapp.R
import com.example.mywikidexapp.data.Favorite
import com.example.mywikidexapp.data.FavoritesViewModel
import com.example.mywikidexapp.data.FavoritesViewModelFactory
import com.example.mywikidexapp.ui.components.FavoritesListItem
import com.example.mywikidexapp.ui.theme.MyWikiDexAppTheme

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
fun FavoritesScreenComposable(
    viewModel: FavoritesViewModel = ViewModelProvider(
        LocalContext.current as ComponentActivity,
        FavoritesViewModelFactory(LocalContext.current)
    ).get(FavoritesViewModel::class.java),
    onNavigateToWiki: (String) -> Unit
) {
    val favoritesList by viewModel.favorites.collectAsState()

    if (favoritesList.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
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
        }
    } else {
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
                        //favoritesList.remove(favorite)
                        viewModel.delete(favorite)
                    }
                )
            }
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