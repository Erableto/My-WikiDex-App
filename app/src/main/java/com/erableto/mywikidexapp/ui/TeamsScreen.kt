package com.erableto.mywikidexapp.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.model.PKMN
import com.erableto.mywikidexapp.data.Team
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
fun TeamsScreen(
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

    var searchQuery by remember {
        mutableStateOf<String?>(null)
    }

    Box(modifier = Modifier.fillMaxSize()) {
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
        } else {*/
            Column {
                TextField(
                    value = searchQuery ?: "",
                    onValueChange = {
                        searchQuery = it
                        // TODO
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    placeholder = {
                        Text("Buscar equipos")
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.rounded_search_24),
                            contentDescription = "Buscar"
                        )
                    },
                    trailingIcon = {
                        if (!searchQuery.isNullOrEmpty()) {
                            IconButton(
                                onClick = {
                                    searchQuery = ""
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
                        TeamsListItem(
                            Team(
                                name = "Equipo",
                                pkmn1 = PKMN(
                                    pkmnName = "Pichu",
                                    itemName = null,
                                    pkmnIcon = "${R.drawable.ic_pkmn_0172}",
                                    itemIcon = null,
                                    type1 = ELECTRIC_TYPE,
                                    type2 = null,
                                    gender = GENDER_FEMALE,
                                    lv = 100,
                                    ability = "Electricidad Estática"
                                ),
                                pkmn2 = PKMN(
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
                                ),
                                pkmn3 = PKMN(
                                    pkmnName = "Giratina",
                                    itemName = "Griseosfera",
                                    pkmnIcon = "${R.drawable.ic_pkmn_0487_b}",
                                    itemIcon = "${R.drawable.ic_item_griseous_orb}",
                                    type1 = DRAGON_TYPE,
                                    type2 = GHOST_TYPE,
                                    lv = 100,
                                    ability = "Levitación"
                                ),
                                pkmn4 = null,
                                pkmn5 = null,
                                pkmn6 = null
                            )
                        )

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