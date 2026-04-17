package com.erableto.mywikidexapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme
import com.erableto.mywikidexapp.util.DRAGON_TYPE
import com.erableto.mywikidexapp.util.GHOST_TYPE
import com.erableto.mywikidexapp.util.getTypeColor
import kotlin.String

@Composable
fun PKMNSlot(
    pkmnName: String?,
    itemName: String?,
    pkmnIcon: Painter?,
    itemIcon: Painter?,
    type1: String?,
    type2: String?
) {
    val color1 = getTypeColor(type1)
    val color2 = if (type2 != null) getTypeColor(type2) else color1

    GradientBoxCard(
        modifier = Modifier
            .clickable{
                // TODO
            },
        colors = listOf(color1, color2),
        padding = PaddingValues(4.dp)
    ) {
        PKMNIcon(
            pkmnName = pkmnName,
            itemName = itemName,
            pkmnIcon = pkmnIcon,
            itemIcon = itemIcon
        )
    }
}

@Composable
@Preview
fun PKMNSlotPreview() {
    MyWikiDexAppTheme() {
        PKMNSlot(
            pkmnName = "Giratina",
            itemName = "Griseosfera",
            pkmnIcon = painterResource(R.drawable.ic_pkmn_0487_b),
            itemIcon = painterResource(R.drawable.ic_item_griseous_orb),
            type1 = DRAGON_TYPE,
            type2 = GHOST_TYPE
        )
    }
}