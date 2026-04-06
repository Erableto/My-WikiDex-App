package com.erableto.mywikidexapp.ui

import android.widget.Toast
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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme

/*
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
*/

@Composable
fun TeamsScreen(
    /*
    viewModel: FavoritesViewModel = ViewModelProvider(
        LocalActivity.current as ComponentActivity,
        FavoritesViewModelFactory(LocalContext.current)
    )[FavoritesViewModel::class.java], // ).get(FavoritesViewModel::class.java),
    onNavigateToWiki: (String) -> Unit
    */
) {
    val context = LocalContext.current

    //val favoritesList by viewModel.favorites.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        //if (favoritesList.isEmpty()) {
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
                    painter = painterResource(R.drawable.rounded_catching_pokemon_250),
                    contentDescription = "No hay equipos",
                    colorFilter = if (isSystemInDarkTheme()) {
                        ColorFilter.tint(Color.White)
                    } else {
                        ColorFilter.tint(Color.Black)
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "No hay equipos",
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp
                )
            }
        /*} else {
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
        }*/

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = {
                // TODO
                Toast.makeText(context, "Soon™", Toast.LENGTH_SHORT).show()
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.rounded_add_24),
                contentDescription = "Crear equipo"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TeamsScreenPreview() {
    //var DUMMY: String

    MyWikiDexAppTheme() {
        TeamsScreen(/*onNavigateToWiki = { url ->
            DUMMY = url
        }*/)
    }
}