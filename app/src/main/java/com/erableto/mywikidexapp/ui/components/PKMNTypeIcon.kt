package com.erableto.mywikidexapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme
import com.erableto.mywikidexapp.util.FIRE_TYPE
import com.erableto.mywikidexapp.util.getTypeName
import com.erableto.mywikidexapp.util.getTypePainter

@Composable
fun PKMNTypeIcon(modifier: Modifier = Modifier, type: String?) {
    Image(
        modifier = modifier,
        painter = getTypePainter(type),
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