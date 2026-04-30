package com.erableto.mywikidexapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.data.PKMN
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme
import com.erableto.mywikidexapp.util.DRAGON_TYPE
import com.erableto.mywikidexapp.util.GHOST_TYPE
import com.erableto.mywikidexapp.util.getTypeColor

@Composable
fun PKMNSlot(pkmn: PKMN?) {
    val color1 = getTypeColor(pkmn?.type1)
    val color2 = if (pkmn?.type2 != null) getTypeColor(pkmn.type2) else color1

    GradientBoxCard(
        modifier = Modifier
            .clickable{
                // TODO
            },
        colors = listOf(color1, color2),
        padding = PaddingValues(4.dp)
    ) {
        PKMNIcon(pkmn = pkmn)
    }
}

@Preview(showBackground = true)
@Composable
fun PKMNSlotPreview() {
    MyWikiDexAppTheme() {
        PKMNSlot(
            pkmn = PKMN(
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