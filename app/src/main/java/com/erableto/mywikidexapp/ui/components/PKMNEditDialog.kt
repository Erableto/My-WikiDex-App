package com.erableto.mywikidexapp.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.data.PKMN
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme
import com.erableto.mywikidexapp.util.FIGHTING_TYPE
import com.erableto.mywikidexapp.util.FIRE_TYPE
import com.erableto.mywikidexapp.util.GENDER_MALE

@Composable
fun PKMNEditDialog(
    pkmn: PKMN?,
    isNew: Boolean = false
) {
    /** DATOS **/
    var pkmnName by remember {
        mutableStateOf(pkmn?.pkmnName)
    }
    var pkmnIcon by remember {
        mutableStateOf(pkmn?.itemIcon)
    }
    var type1 by remember {
        mutableStateOf(pkmn?.type1)
    }
    var type2 by remember {
        mutableStateOf(pkmn?.type2)
    }
    var lv by remember {
        mutableIntStateOf(pkmn?.lv ?: 0)
    }
    var gender by remember {
        mutableStateOf(pkmn?.gender)
    }
    var ability by remember {
        mutableStateOf(pkmn?.ability)
    }
    var itemName by remember {
        mutableStateOf(pkmn?.itemName)
    }
    var itemIcon by remember {
        mutableStateOf(pkmn?.itemIcon)
    }
    var mov1 by remember {
        mutableStateOf(pkmn?.mov1)
    }
    var mov2 by remember {
        mutableStateOf(pkmn?.mov2)
    }
    var mov3 by remember {
        mutableStateOf(pkmn?.mov3)
    }
    var mov4 by remember {
        mutableStateOf(pkmn?.mov4)
    }
    /** ----- **/

    var showDialog by remember {
        mutableStateOf(false)
    }

    AlertDialog(
        onDismissRequest = {
            showDialog = false
        },
        title = {
            Text(if (isNew) "Añadir Pokémon" else "Editar Pokémon")
        },
        text = {
            Text("¿Quieres borrar el historial?")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    // TODO

                    showDialog = false
                }
            ) {
                Text("GUARDAR")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    showDialog = false
                }
            ) {
                Text("SALIR")
            }
        }
    )
}

@Composable
@Preview
fun PKMNEditDialogPreview() {
    MyWikiDexAppTheme() {
        PKMNEditDialog(
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