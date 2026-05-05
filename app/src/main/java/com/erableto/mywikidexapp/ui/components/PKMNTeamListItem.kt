package com.erableto.mywikidexapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.model.PKMN
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme
import com.erableto.mywikidexapp.util.FIGHTING_TYPE
import com.erableto.mywikidexapp.util.FIRE_TYPE
import com.erableto.mywikidexapp.util.GENDER_FEMALE
import com.erableto.mywikidexapp.util.GENDER_MALE
import com.erableto.mywikidexapp.util.getTypeColor

@Composable
fun PKMNTeamListItem(pkmn: PKMN) {
    val pkmnIcon: Painter? =
        if (pkmn.pkmnIcon != null && pkmn.pkmnIcon.isDigitsOnly()) painterResource(pkmn.pkmnIcon.toInt())
        else null
    val itemIcon: Painter? =
        if (pkmn.itemIcon != null && pkmn.itemIcon.isDigitsOnly()) painterResource(pkmn.itemIcon.toInt())
        else null

    var showMenu by remember {
        mutableStateOf(false)
    }

    val color1 = getTypeColor(pkmn.type1)
    val color2 = if (pkmn.type2 != null) getTypeColor(pkmn.type2) else color1

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .clickable {
                    showMenu = true
                }
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                GradientBoxCard(
                    modifier = Modifier.fillMaxWidth(),
                    colors = listOf(color1, color2),
                    padding = PaddingValues(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        PKMNIcon(pkmn = pkmn)

                        Spacer(modifier = Modifier.width(8.dp))

                        val inlineContentId = "genderIcon"
                        val text = buildAnnotatedString {
                            append(pkmn.pkmnName ?: "MissingNo.")
                            append(" ")
                            appendInlineContent(inlineContentId, "[genderIcon]")
                            append("    Nv. ${pkmn.lv}")
                        }

                        Column(verticalArrangement = Arrangement.Center) {
                            Text(
                                text = text,
                                textAlign = TextAlign.Start,
                                color = Color.White,
                                inlineContent = mapOf(
                                    inlineContentId to InlineTextContent(
                                        placeholder = Placeholder(
                                            width = 20.sp,
                                            height = 20.sp,
                                            placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                                        )
                                    ) {
                                        if (pkmn.gender == GENDER_MALE || pkmn.gender == GENDER_FEMALE) {
                                            Card(
                                                shape = RoundedCornerShape(4.dp),
                                                colors = CardColors(
                                                    Color.White,
                                                    Color.White,
                                                    Color.White,
                                                    Color.White
                                                )
                                            ) {
                                                PKMNGenderIcon(gender = pkmn.gender)
                                            }
                                        }
                                    }
                                )
                            )

                            Text(
                                text = "Habilidad: ${pkmn.ability}",
                                textAlign = TextAlign.Start,
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Card(
                            shape = RoundedCornerShape(4.dp),
                            colors = CardColors(
                                Color.White,
                                Color.White,
                                Color.White,
                                Color.White
                            )
                        ) {
                            Row(
                                modifier = Modifier.padding(4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                PKMNTypeIcon(modifier = Modifier.size(25.dp), type = pkmn.type1)

                                if (pkmn.type2 != null) {
                                    Spacer(modifier = Modifier.width(4.dp))

                                    PKMNTypeIcon(modifier = Modifier.size(25.dp), type = pkmn.type2)
                                }
                            }
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = pkmn.mov1, textAlign = TextAlign.Center)

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = pkmn.mov3, textAlign = TextAlign.Center)
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = pkmn.mov2, textAlign = TextAlign.Center)

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = pkmn.mov4, textAlign = TextAlign.Center)
                    }
                }
            }
        }

        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = {
                showMenu = false
            }
        ) {
            DropdownMenuItem(
                text = {
                    Text("Editar Pokémon")
                },
                onClick = {
                    showMenu = false
                    // TODO
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.rounded_edit_24),
                        contentDescription = "Editar Pokémon"
                    )
                }
            )

            DropdownMenuItem(
                text = {
                    Text("Eliminar Pokémon")
                },
                onClick = {
                    showMenu = false
                    // TODO
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.rounded_delete_24),
                        contentDescription = "Eliminar Pokémon"
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PKMNTeamListItemPreview() {
    MyWikiDexAppTheme() {
        PKMNTeamListItem(
            pkmn = PKMN(
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
    }
}