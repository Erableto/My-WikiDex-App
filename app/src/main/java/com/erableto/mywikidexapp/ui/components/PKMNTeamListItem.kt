package com.erableto.mywikidexapp.ui.components

import androidx.compose.foundation.Image
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
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme
import com.erableto.mywikidexapp.util.FIGHTING_TYPE
import com.erableto.mywikidexapp.util.FIRE_TYPE
import com.erableto.mywikidexapp.util.GENDER_FEMALE
import com.erableto.mywikidexapp.util.GENDER_MALE
import com.erableto.mywikidexapp.util.GENDER_UNKNOWN
import com.erableto.mywikidexapp.util.getTypeColor

@Composable
fun PKMNTeamListItem(
    pkmnName: String?,
    itemName: String?,
    pkmnIcon: Painter?,
    itemIcon: Painter?,
    type1: String?,
    type2: String?,
    gender: String = GENDER_UNKNOWN,
    lv: Int = 0,
    ability: String = "-",
    mov1: String = "-",
    mov2: String = "-",
    mov3: String = "-",
    mov4: String = "-"
) {
    var showMenu by remember {
        mutableStateOf(false)
    }

    val color1 = getTypeColor(type1)
    val color2 = if (type2 != null) getTypeColor(type2) else color1

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
                        PKMNIcon(
                            pkmnName = pkmnName,
                            itemName = itemName,
                            pkmnIcon = pkmnIcon,
                            itemIcon = itemIcon
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        val inlineContentId = "genderIcon"
                        val text = buildAnnotatedString {
                            append(pkmnName ?: "MissingNo.")
                            append(" ")
                            appendInlineContent(inlineContentId, "[genderIcon]")
                            append("    Nv. $lv")
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
                                        val painterResource = when (gender) {
                                            GENDER_MALE -> {
                                                painterResource(R.drawable.rounded_male_24)
                                            }

                                            GENDER_FEMALE -> {
                                                painterResource(R.drawable.rounded_female_24)
                                            }

                                            GENDER_UNKNOWN -> {
                                                painterResource(R.drawable.rounded_blank_24)
                                            }

                                            else -> {
                                                painterResource(R.drawable.rounded_blank_24)
                                            }
                                        }

                                        val contentDescription = when (gender) {
                                            GENDER_MALE -> {
                                                "Macho"
                                            }

                                            GENDER_FEMALE -> {
                                                "Hembra"
                                            }

                                            GENDER_UNKNOWN -> {
                                                "Desconocido"
                                            }

                                            else -> {
                                                "Desconocido"
                                            }
                                        }

                                        Image(
                                            painter = painterResource,
                                            contentDescription = contentDescription
                                        )
                                    }
                                )
                            )

                            Text(
                                text = "Habilidad: $ability",
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
                                PKMNTypeIcon(modifier = Modifier.size(25.dp), type = type1)

                                Spacer(modifier = Modifier.width(4.dp))

                                PKMNTypeIcon(modifier = Modifier.size(25.dp), type = type2)
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
                        Text(text = mov1, textAlign = TextAlign.Center)

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = mov3, textAlign = TextAlign.Center)
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = mov2, textAlign = TextAlign.Center)

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(text = mov4, textAlign = TextAlign.Center)
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

@Composable
@Preview
fun PKMNTeamListItemPreview() {
    MyWikiDexAppTheme() {
        PKMNTeamListItem(
            pkmnName = "Infernape",
            itemName = "Carbón",
            pkmnIcon = painterResource(R.drawable.ic_pkmn_0392),
            itemIcon = painterResource(R.drawable.ic_item_charcoal),
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
    }
}