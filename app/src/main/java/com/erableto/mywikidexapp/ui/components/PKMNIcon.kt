package com.erableto.mywikidexapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.Modifier.Companion
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme

@Composable
fun PKMNIcon(
    modifier: Modifier = Modifier,
    pkmnName: String?,
    itemName: String?,
    pkmnIcon: Painter?,
    itemIcon: Painter?
) {
    Box(modifier = modifier) {
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
fun PKMNIconPreview() {
    MyWikiDexAppTheme() {
        PKMNIcon(
            pkmnName = "Giratina",
            itemName = "Griseosfera",
            pkmnIcon = painterResource(R.drawable.ic_pkmn_0487_b),
            itemIcon = painterResource(R.drawable.ic_item_griseous_orb)
        )
    }
}