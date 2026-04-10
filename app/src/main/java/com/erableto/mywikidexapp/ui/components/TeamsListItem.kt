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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme
import com.erableto.mywikidexapp.utils.DRAGON_TYPE
import com.erableto.mywikidexapp.utils.ELECTRIC_TYPE
import com.erableto.mywikidexapp.utils.FIGHTING_TYPE
import com.erableto.mywikidexapp.utils.FIRE_TYPE
import com.erableto.mywikidexapp.utils.GHOST_TYPE

@Composable
fun TeamsListItem() {
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
        Column(modifier = Modifier.padding(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Equipo")

                Spacer(modifier = Modifier.width(8.dp).weight(1f))

                Row {
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
                    PKMNSlot(
                        pkmnName = null,
                        itemName = null,
                        pkmnIcon = painterResource(R.drawable.ic_pkmn_0172),
                        itemIcon = null,
                        type1 = ELECTRIC_TYPE,
                        type2 = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    PKMNSlot(
                        pkmnName = null,
                        itemName = null,
                        pkmnIcon = painterResource(R.drawable.ic_pkmn_0392),
                        itemIcon = painterResource(R.drawable.ic_item_charcoal),
                        type1 = FIRE_TYPE,
                        type2 = FIGHTING_TYPE
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    PKMNSlot(
                        pkmnName = null,
                        itemName = null,
                        pkmnIcon = painterResource(R.drawable.ic_pkmn_0487_b),
                        itemIcon = painterResource(R.drawable.ic_item_griseous_orb),
                        type1 = DRAGON_TYPE,
                        type2 = GHOST_TYPE
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    PKMNSlot(
                        pkmnName = null,
                        itemName = null,
                        pkmnIcon = null,
                        itemIcon = null,
                        type1 = null,
                        type2 = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    PKMNSlot(
                        pkmnName = null,
                        itemName = null,
                        pkmnIcon = null,
                        itemIcon = null,
                        type1 = null,
                        type2 = null
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    PKMNSlot(
                        pkmnName = null,
                        itemName = null,
                        pkmnIcon = null,
                        itemIcon = null,
                        type1 = null,
                        type2 = null
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun TeamsListItemPreview() {
    MyWikiDexAppTheme() {
        TeamsListItem()
    }
}