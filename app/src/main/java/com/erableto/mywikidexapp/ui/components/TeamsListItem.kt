package com.erableto.mywikidexapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.data.PKMN
import com.erableto.mywikidexapp.data.Team
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme
import com.erableto.mywikidexapp.util.DRAGON_TYPE
import com.erableto.mywikidexapp.util.ELECTRIC_TYPE
import com.erableto.mywikidexapp.util.FIGHTING_TYPE
import com.erableto.mywikidexapp.util.FIRE_TYPE
import com.erableto.mywikidexapp.util.GENDER_FEMALE
import com.erableto.mywikidexapp.util.GENDER_MALE
import com.erableto.mywikidexapp.util.GHOST_TYPE

@Composable
fun TeamsListItem(team: Team) {
    var showMenu by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable {
                // TODO
            }
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Equipo")

                Spacer(modifier = Modifier.width(8.dp).weight(1f))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = {
                            showMenu = true
                        }
                    ) {
                        Icon(
                            painterResource(id = R.drawable.rounded_more_vert_24),
                            contentDescription = "Opciones"
                        )
                    }

                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = {
                            showMenu = false
                        }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text("Renombrar equipo")
                            },
                            onClick = {
                                showMenu = false
                                // TODO
                            },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.rounded_edit_24),
                                    contentDescription = "Renombrar equipo"
                                )
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text("Compartir equipo como QR")
                            },
                            onClick = {
                                showMenu = false
                                // TODO
                            },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.rounded_qr_code_24),
                                    contentDescription = "Compartir equipo como QR"
                                )
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text("Eliminar equipo")
                            },
                            onClick = {
                                showMenu = false
                                // TODO
                            },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(R.drawable.rounded_delete_24),
                                    contentDescription = "Eliminar equipo"
                                )
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                item {
                    PKMNSlot(pkmn = team.pkmn1)

                    Spacer(modifier = Modifier.width(8.dp))

                    val pkmnIcon2: Painter? =
                        if (team.pkmn2?.pkmnIcon != null && team.pkmn2.pkmnIcon.isDigitsOnly()) painterResource(team.pkmn2.pkmnIcon.toInt())
                        else null
                    val itemIcon2: Painter? =
                        if (team.pkmn2?.itemIcon != null && team.pkmn2.itemIcon.isDigitsOnly()) painterResource(team.pkmn2.itemIcon.toInt())
                        else null

                    PKMNSlot(pkmn = team.pkmn2)

                    Spacer(modifier = Modifier.width(8.dp))

                    PKMNSlot(pkmn = team.pkmn3)

                    Spacer(modifier = Modifier.width(8.dp))

                    PKMNSlot(pkmn = team.pkmn4)

                    Spacer(modifier = Modifier.width(8.dp))

                    PKMNSlot(pkmn = team.pkmn5)

                    Spacer(modifier = Modifier.width(8.dp))

                    PKMNSlot(pkmn = team.pkmn6)
                }
            }
        }
    }
}

@Composable
@Preview
fun TeamsListItemPreview() {
    MyWikiDexAppTheme() {
        TeamsListItem(
            team = Team(
                teamName = "Equipo",
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
    }
}