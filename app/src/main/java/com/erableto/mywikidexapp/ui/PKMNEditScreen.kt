package com.erableto.mywikidexapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.data.PKMN
import com.erableto.mywikidexapp.ui.components.PKMNGenderIcon
import com.erableto.mywikidexapp.ui.components.PKMNTypeIcon
import com.erableto.mywikidexapp.ui.components.RoundedDropdownMenuTrailingIcon
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme
import com.erableto.mywikidexapp.util.BUG_TYPE
import com.erableto.mywikidexapp.util.DARK_TYPE
import com.erableto.mywikidexapp.util.DRAGON_TYPE
import com.erableto.mywikidexapp.util.ELECTRIC_TYPE
import com.erableto.mywikidexapp.util.FAIRY_TYPE
import com.erableto.mywikidexapp.util.FIGHTING_TYPE
import com.erableto.mywikidexapp.util.FIRE_TYPE
import com.erableto.mywikidexapp.util.FLYING_TYPE
import com.erableto.mywikidexapp.util.GENDER_FEMALE
import com.erableto.mywikidexapp.util.GENDER_MALE
import com.erableto.mywikidexapp.util.GENDER_UNKNOWN
import com.erableto.mywikidexapp.util.GHOST_TYPE
import com.erableto.mywikidexapp.util.GRASS_TYPE
import com.erableto.mywikidexapp.util.GROUND_TYPE
import com.erableto.mywikidexapp.util.ICE_TYPE
import com.erableto.mywikidexapp.util.NORMAL_TYPE
import com.erableto.mywikidexapp.util.POISON_TYPE
import com.erableto.mywikidexapp.util.PSYCHIC_TYPE
import com.erableto.mywikidexapp.util.ROCK_TYPE
import com.erableto.mywikidexapp.util.STEEL_TYPE
import com.erableto.mywikidexapp.util.WATER_TYPE
import com.erableto.mywikidexapp.util.getGenderName
import com.erableto.mywikidexapp.util.getTypeName

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PKMNEditScreen(
    pkmn: PKMN?,
    isNew: Boolean = false
) {
    /** DATOS **/
    var pkmnName by remember {
        mutableStateOf(pkmn?.pkmnName ?: "")
    }
    var pkmnIcon by remember {
        mutableStateOf(pkmn?.itemIcon ?: R.drawable.rounded_blank_24)
    }
    var type1 by remember {
        mutableStateOf(pkmn?.type1 ?: "")
    }
    var type2 by remember {
        mutableStateOf(pkmn?.type2)
    }
    var lv by remember {
        mutableIntStateOf(pkmn?.lv ?: 0)
    }
    var gender by remember {
        mutableStateOf(pkmn?.gender ?: GENDER_UNKNOWN)
    }
    var ability by remember {
        mutableStateOf(pkmn?.ability ?: "")
    }
    var itemName by remember {
        mutableStateOf(pkmn?.itemName ?: "-")
    }
    var itemIcon by remember {
        mutableStateOf(pkmn?.itemIcon ?: R.drawable.rounded_blank_24)
    }
    var mov1 by remember {
        mutableStateOf(pkmn?.mov1 ?: "-")
    }
    var mov2 by remember {
        mutableStateOf(pkmn?.mov2 ?: "-")
    }
    var mov3 by remember {
        mutableStateOf(pkmn?.mov3 ?: "-")
    }
    var mov4 by remember {
        mutableStateOf(pkmn?.mov4 ?: "-")
    }
    /** ----- **/

    var expandedType1Spinner by remember {
        mutableStateOf(false)
    }
    var expandedType2Spinner by remember {
        mutableStateOf(false)
    }
    var expandedGenderSpinner by remember {
        mutableStateOf(false)
    }
    
    val typeOptions = listOf(
        "",
        NORMAL_TYPE,
        GRASS_TYPE,
        FIRE_TYPE,
        WATER_TYPE,
        ELECTRIC_TYPE,
        BUG_TYPE,
        FLYING_TYPE,
        ROCK_TYPE,
        POISON_TYPE,
        GROUND_TYPE,
        ICE_TYPE,
        FIGHTING_TYPE,
        PSYCHIC_TYPE,
        GHOST_TYPE,
        DRAGON_TYPE,
        DARK_TYPE,
        STEEL_TYPE,
        FAIRY_TYPE
    )
    val genderOptions = listOf(
        GENDER_UNKNOWN,
        GENDER_MALE,
        GENDER_FEMALE
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    modifier = Modifier
                        .height(50.dp)
                        .padding(8.dp)
                        .wrapContentHeight(Alignment.CenterVertically),
                    text = if (isNew) "Añadir Pokémon" else "Editar Pokémon"
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
                    .weight(1.0f)
            ) {
                item {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = pkmnName,
                        onValueChange = {
                            pkmnName = it
                        },
                        label = {
                            Text("Pokémon")
                        },
                        isError = pkmnName.isBlank()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        ExposedDropdownMenuBox(
                            modifier = Modifier.weight(1.0f),
                            expanded = expandedType1Spinner,
                            onExpandedChange = {
                                expandedType1Spinner = !expandedType1Spinner
                            }
                        ) {
                            OutlinedTextField(
                                value = getTypeName(type1),
                                onValueChange = {
                                    //type1 = it
                                },
                                readOnly = true,
                                label = {
                                    Text("Tipo 1")
                                },
                                isError = type1.isBlank(),
                                leadingIcon = {
                                    PKMNTypeIcon(modifier = Modifier.size(25.dp), type = type1)
                                },
                                trailingIcon = {
                                    // Icono de flecha que rota automáticamente
                                    //ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedType1Spinner)
                                    RoundedDropdownMenuTrailingIcon(expanded = expandedType1Spinner)
                                },
                                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                                modifier = Modifier
                                    .menuAnchor() // IMPORTANTE: ancla el menú al TextField
                                    //.fillMaxWidth()
                            )

                            ExposedDropdownMenu(
                                expanded = expandedType1Spinner,
                                onDismissRequest = {
                                    expandedType1Spinner = false
                                }
                            ) {
                                typeOptions.forEach { option ->
                                    DropdownMenuItem(
                                        text = {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Start
                                            ) {
                                                PKMNTypeIcon(
                                                    modifier = Modifier.size(25.dp),
                                                    type = option
                                                )

                                                Spacer(modifier = Modifier.width(8.dp))

                                                Text(
                                                    modifier = Modifier.wrapContentHeight(Alignment.CenterVertically),
                                                    text = getTypeName(option)
                                                )
                                            }
                                        },
                                        onClick = {
                                            type1 = option
                                            expandedType1Spinner = false
                                        },
                                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        ExposedDropdownMenuBox(
                            modifier = Modifier.weight(1.0f),
                            expanded = expandedType2Spinner,
                            onExpandedChange = {
                                expandedType2Spinner = !expandedType2Spinner
                            }
                        ) {
                            OutlinedTextField(
                                value = getTypeName(type2),
                                onValueChange = {
                                    //type2 = it
                                },
                                readOnly = true,
                                label = {
                                    Text("Tipo 2")
                                },
                                //isError = type2.isBlank(),
                                leadingIcon = {
                                    PKMNTypeIcon(modifier = Modifier.size(25.dp), type = type2)
                                },
                                trailingIcon = {
                                    // Icono de flecha que rota automáticamente
                                    //ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedType2Spinner)
                                    RoundedDropdownMenuTrailingIcon(expanded = expandedType2Spinner)
                                },
                                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                                modifier = Modifier
                                    .menuAnchor() // IMPORTANTE: ancla el menú al TextField
                                    //.fillMaxWidth()
                            )

                            ExposedDropdownMenu(
                                expanded = expandedType2Spinner,
                                onDismissRequest = {
                                    expandedType2Spinner = false
                                }
                            ) {
                                typeOptions.forEach { option ->
                                    DropdownMenuItem(
                                        text = {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Start
                                            ) {
                                                PKMNTypeIcon(
                                                    modifier = Modifier.size(25.dp),
                                                    type = option
                                                )

                                                Spacer(modifier = Modifier.width(8.dp))

                                                Text(
                                                    modifier = Modifier.wrapContentHeight(Alignment.CenterVertically),
                                                    text = getTypeName(option)
                                                )
                                            }
                                        },
                                        onClick = {
                                            type2 = option
                                            expandedType2Spinner = false
                                        },
                                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.weight(1.0f),
                            value = lv.toString(),
                            onValueChange = { newValue ->
                                val onlyNumbers = newValue.filter {
                                    it.isDigit()
                                }

                                if (onlyNumbers.length <= 3) {
                                    lv = onlyNumbers.toIntOrNull() ?: 0

                                    if (lv > 100) lv = 100
                                }
                            },
                            label = {
                                Text("Nivel")
                            },
                            isError = lv.toString().isBlank(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        ExposedDropdownMenuBox(
                            modifier = Modifier.weight(1.0f),
                            expanded = expandedGenderSpinner,
                            onExpandedChange = {
                                expandedGenderSpinner = !expandedGenderSpinner
                            }
                        ) {
                            OutlinedTextField(
                                value = getGenderName(gender),
                                onValueChange = {
                                    //gender = it
                                },
                                readOnly = true,
                                label = {
                                    Text("Sexo")
                                },
                                isError = gender.isBlank(),
                                leadingIcon = {
                                    PKMNGenderIcon(gender = gender)
                                },
                                trailingIcon = {
                                    // Icono de flecha que rota automáticamente
                                    //ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedGenderSpinner)
                                    RoundedDropdownMenuTrailingIcon(expanded = expandedGenderSpinner)
                                },
                                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                                modifier = Modifier
                                    .menuAnchor() // IMPORTANTE: ancla el menú al TextField
                                //.fillMaxWidth()
                            )

                            ExposedDropdownMenu(
                                expanded = expandedGenderSpinner,
                                onDismissRequest = {
                                    expandedGenderSpinner = false
                                }
                            ) {
                                genderOptions.forEach { option ->
                                    DropdownMenuItem(
                                        text = {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Start
                                            ) {
                                                PKMNGenderIcon(gender = option)

                                                Spacer(modifier = Modifier.width(8.dp))

                                                Text(
                                                    modifier = Modifier.wrapContentHeight(Alignment.CenterVertically),
                                                    text = getGenderName(option)
                                                )
                                            }
                                        },
                                        onClick = {
                                            gender = option
                                            expandedGenderSpinner = false
                                        },
                                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = ability,
                        onValueChange = {
                            ability = it
                        },
                        label = {
                            Text("Habilidad")
                        },
                        //isError = ability.isBlank()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = itemName,
                        onValueChange = {
                            itemName = it
                        },
                        label = {
                            Text("Objeto")
                        },
                        //isError = itemName.isBlank()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.weight(1.0f),
                            value = mov1,
                            onValueChange = {
                                mov1 = it
                            },
                            label = {
                                Text("Movimiento 1")
                            },
                            //isError = mov1.isBlank()
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        OutlinedTextField(
                            modifier = Modifier.weight(1.0f),
                            value = mov2,
                            onValueChange = {
                                mov2 = it
                            },
                            label = {
                                Text("Movimiento 2")
                            },
                            //isError = mov2.isBlank()
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.weight(1.0f),
                            value = mov3,
                            onValueChange = {
                                mov3 = it
                            },
                            label = {
                                Text("Movimiento 3")
                            },
                            //isError = mov3.isBlank()
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        OutlinedTextField(
                            modifier = Modifier.weight(1.0f),
                            value = mov4,
                            onValueChange = {
                                mov4 = it
                            },
                            label = {
                                Text("Movimiento 4")
                            },
                            //isError = mov4.isBlank()
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(
                    onClick = {
                        // TODO
                    }
                ) {
                    Text("GUARDAR")
                }

                Spacer(modifier = Modifier.width(8.dp))

                TextButton(
                    onClick = {
                        // TODO
                    }
                ) {
                    Text("SALIR")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PKMNEditScreenPreview() {
    MyWikiDexAppTheme() {
        PKMNEditScreen(
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