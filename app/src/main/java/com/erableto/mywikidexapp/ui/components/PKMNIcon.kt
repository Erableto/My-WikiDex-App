package com.erableto.mywikidexapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.data.PKMN
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme
import com.erableto.mywikidexapp.util.DRAGON_TYPE
import com.erableto.mywikidexapp.util.GHOST_TYPE

@Composable
fun PKMNIcon(
    modifier: Modifier = Modifier,
    pkmn: PKMN?
) {
    Box(modifier = modifier) {
        val pkmnIcon: Painter? =
            if (pkmn?.pkmnIcon != null && pkmn.pkmnIcon.isDigitsOnly()) painterResource(pkmn.pkmnIcon.toInt())
            else null
        val itemIcon: Painter? =
            if (pkmn?.itemIcon != null && pkmn.itemIcon.isDigitsOnly()) painterResource(pkmn.itemIcon.toInt())
            else null

        Image(
            modifier = Modifier.align(Alignment.Center).size(50.dp),
            painter = pkmnIcon ?: painterResource(R.drawable.rounded_catching_pokemon_50),
            contentDescription = pkmn?.pkmnName,
            colorFilter = if (pkmnIcon == null) ColorFilter.tint(LocalContentColor.current) else null
        )

        if (itemIcon != null) {
            Image(
                modifier = Modifier.align(Alignment.BottomEnd).size(25.dp),
                painter = itemIcon,
                contentDescription = pkmn?.itemName
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PKMNIconPreview() {
    MyWikiDexAppTheme() {
        PKMNIcon(
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