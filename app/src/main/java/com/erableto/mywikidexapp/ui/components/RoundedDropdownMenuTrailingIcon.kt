package com.erableto.mywikidexapp.ui.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme

@Composable
fun RoundedDropdownMenuTrailingIcon(expanded: Boolean, modifier: Modifier = Modifier) {
    Icon(
        painter = painterResource(id = R.drawable.rounded_arrow_drop_down_24),
        contentDescription = null,
        modifier = modifier.rotate(if (expanded) 180f else 0f)
    )
}

@Preview(showBackground = true)
@Composable
fun RoundedDropdownMenuTrailingIconPreview() {
    MyWikiDexAppTheme() {
        RoundedDropdownMenuTrailingIcon(false)
    }
}