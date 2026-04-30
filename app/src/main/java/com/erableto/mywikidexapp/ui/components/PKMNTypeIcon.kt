package com.erableto.mywikidexapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme
import com.erableto.mywikidexapp.util.BUG_TYPE
import com.erableto.mywikidexapp.util.DARK_TYPE
import com.erableto.mywikidexapp.util.DRAGON_TYPE
import com.erableto.mywikidexapp.util.ELECTRIC_TYPE
import com.erableto.mywikidexapp.util.FAIRY_TYPE
import com.erableto.mywikidexapp.util.FIGHTING_TYPE
import com.erableto.mywikidexapp.util.FIRE_TYPE
import com.erableto.mywikidexapp.util.FLYING_TYPE
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
import com.erableto.mywikidexapp.util.getTypeName

@Composable
fun PKMNTypeIcon(modifier: Modifier = Modifier, type: String?) {
    val painterResource = when (type) {
        NORMAL_TYPE -> painterResource(R.drawable.ic_type_normal)
        GRASS_TYPE -> painterResource(R.drawable.ic_type_grass)
        FIRE_TYPE -> painterResource(R.drawable.ic_type_fire)
        WATER_TYPE -> painterResource(R.drawable.ic_type_water)
        ELECTRIC_TYPE -> painterResource(R.drawable.ic_type_electric)
        BUG_TYPE -> painterResource(R.drawable.ic_type_bug)
        FLYING_TYPE -> painterResource(R.drawable.ic_type_flying)
        ROCK_TYPE -> painterResource(R.drawable.ic_type_rock)
        POISON_TYPE -> painterResource(R.drawable.ic_type_poison)
        GROUND_TYPE -> painterResource(R.drawable.ic_type_ground)
        ICE_TYPE -> painterResource(R.drawable.ic_type_ice)
        FIGHTING_TYPE -> painterResource(R.drawable.ic_type_fighting)
        PSYCHIC_TYPE -> painterResource(R.drawable.ic_type_psychic)
        GHOST_TYPE -> painterResource(R.drawable.ic_type_ghost)
        DRAGON_TYPE -> painterResource(R.drawable.ic_type_dragon)
        DARK_TYPE -> painterResource(R.drawable.ic_type_dark)
        STEEL_TYPE -> painterResource(R.drawable.ic_type_steel)
        FAIRY_TYPE -> painterResource(R.drawable.ic_type_fairy)
        else -> painterResource(R.drawable.ic_type_null)
    }

    Image(
        modifier = modifier,
        painter = painterResource,
        contentDescription = getTypeName(type)
    )
}

@Preview(showBackground = true)
@Composable
fun PKMNTypeIconPreview() {
    MyWikiDexAppTheme() {
        PKMNTypeIcon(type = FIRE_TYPE)
    }
}