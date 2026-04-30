package com.erableto.mywikidexapp.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme

@Composable
fun RoundedDropdownMenuTrailingIcon(expanded: Boolean, modifier: Modifier = Modifier) {
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "RotationAnimation"
    )

    Icon(
        painter = painterResource(id = R.drawable.rounded_arrow_drop_down_24),
        contentDescription = null,
        modifier = modifier.rotate(rotation)
    )

    /*
    Icon(
        painter =
            if (expanded) painterResource(id = R.drawable.rounded_arrow_drop_up_24)
            else painterResource(id = R.drawable.rounded_arrow_drop_down_24),
        contentDescription = null,
        modifier = modifier
    )
    */
}

@Preview(showBackground = true)
@Composable
fun RoundedDropdownMenuTrailingIconPreview() {
    MyWikiDexAppTheme() {
        RoundedDropdownMenuTrailingIcon(false)
    }
}