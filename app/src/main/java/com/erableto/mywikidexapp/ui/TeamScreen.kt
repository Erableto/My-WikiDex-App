package com.erableto.mywikidexapp.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.data.PKMN
import com.erableto.mywikidexapp.ui.components.PKMNTeamListItem
import com.erableto.mywikidexapp.ui.components.TeamsListItem
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme
import com.erableto.mywikidexapp.util.DRAGON_TYPE
import com.erableto.mywikidexapp.util.ELECTRIC_TYPE
import com.erableto.mywikidexapp.util.FIGHTING_TYPE
import com.erableto.mywikidexapp.util.FIRE_TYPE
import com.erableto.mywikidexapp.util.GENDER_FEMALE
import com.erableto.mywikidexapp.util.GENDER_MALE
import com.erableto.mywikidexapp.util.GHOST_TYPE

/*
val TeamsList_ = mutableStateListOf(
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

val teamsList_empty = mutableStateListOf<Favorite>()
*/

@Composable
fun TeamScreen(
    /*
    viewModel: TeamsViewModel = ViewModelProvider(
        LocalActivity.current as ComponentActivity,
        TeamsViewModelFactory(LocalContext.current)
    )[TeamsViewModel::class.java], // ).get(TeamsViewModel::class.java),
    onNavigateToWiki: (String) -> Unit
    */
) {
    val context = LocalContext.current

    //val teamsList by viewModel.teams.collectAsState()

    var teamSize by remember {
        mutableIntStateOf(0)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(
                    onClick = {
                        // TODO
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.rounded_arrow_back_24),
                        contentDescription = "Volver"
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Equipo"
                )
            }
        /*if (teamsList.isEmpty()) {
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
                    contentDescription = "No hay Pokémon en el equipo",
                    colorFilter = if (isSystemInDarkTheme()) {
                        ColorFilter.tint(Color.White)
                    } else {
                        ColorFilter.tint(Color.Black)
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "No hay Pokémon en el equipo",
                    textAlign = TextAlign.Center,
                    fontSize = 32.sp
                )
            }
        } else {*/
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                /*items(favoritesList.size) { index: Int ->
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
                }*/
                item {
                    PKMNTeamListItem(
                        PKMN(
                            pkmnName = "Pichu",
                            itemName = null,
                            pkmnIcon = "${R.drawable.ic_pkmn_0172}",
                            itemIcon = null,
                            type1 = ELECTRIC_TYPE,
                            type2 = null,
                            gender = GENDER_FEMALE,
                            lv = 100,
                            ability = "Electricidad Estática"
                        )
                    )

                    PKMNTeamListItem(
                        PKMN(
                            pkmnName = "Infernape",
                            itemName = "Carbón",
                            pkmnIcon = "${R.drawable.ic_pkmn_0392}",
                            itemIcon = "${R.drawable.ic_item_charcoal}",
                            type1 = FIRE_TYPE,
                            type2 = FIGHTING_TYPE,
                            gender = GENDER_MALE,
                            lv = 100,
                            ability = "Mar Llamas",
                            mov1 = "Envite Ígneo",
                            mov2 = "Golpe Aéreo",
                            mov3 = "A Bocajarro",
                            mov4 = "Excavar"
                        )
                    )

                    PKMNTeamListItem(
                        PKMN(
                            pkmnName = "Giratina",
                            itemName = "Griseosfera",
                            pkmnIcon = "${R.drawable.ic_pkmn_0487_b}",
                            itemIcon = "${R.drawable.ic_item_griseous_orb}",
                            type1 = DRAGON_TYPE,
                            type2 = GHOST_TYPE,
                            lv = 100,
                            ability = "Levitación"
                        )
                    )
                }
            }
        }
        //}

        // Los equipos solo pueden tener un máximo de 6 Pokémon.
        if (teamSize < 6) {
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
                    contentDescription = "Añadir Pokémon"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TeamScreenPreview() {
    //var DUMMY: String

    MyWikiDexAppTheme() {
        TeamScreen(/*onNavigateToWiki = { url ->
            DUMMY = url
        }*/)
    }
}