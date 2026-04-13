package com.erableto.mywikidexapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ColorFilter
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

    Box(
        modifier = Modifier
            .clip(CardDefaults.shape)
            .background(
                Brush.horizontalGradient(
                    colors = listOf(color1, color2)
                )
            )
            .padding(4.dp)
            .clickable{
                // TODO
            }
    ) {
        Image(
            modifier = Modifier.align(Alignment.Center).size(50.dp),
            painter = pkmnIcon ?: painterResource(R.drawable.rounded_catching_pokemon_50),
            contentDescription = pkmnName,
            colorFilter = if (pkmnIcon == null) ColorFilter.tint(LocalContentColor.current) else null
        )

        if (itemIcon != null) {
            Image(
                modifier = Modifier.align(Alignment.BottomEnd).size(25.dp),
                painter = itemIcon,
                contentDescription = itemName
            )
        }
    }
}

@Composable
@Preview
fun PKMNSlotPreview() {
    MyWikiDexAppTheme() {
        PKMNSlot(
            pkmnName = null,
            itemName = null,
            pkmnIcon = painterResource(R.drawable.ic_pkmn_0487_b),
            itemIcon = painterResource(R.drawable.ic_item_griseous_orb),
            type1 = DRAGON_TYPE,
            type2 = GHOST_TYPE
        )
    }
}