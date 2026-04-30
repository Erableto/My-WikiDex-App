package com.erableto.mywikidexapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme

@Composable
fun CardFABItem(
    title: String,
    icon: Painter,
    iconContentDescription: String,
    onClickCard: () -> Unit,
    onClickFAB: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .clickable {
                onClickCard()
            }
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                title,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(8.dp))

            FloatingActionButton(onClick = onClickFAB) {
                Icon(
                    painter = icon,
                    contentDescription = iconContentDescription
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardFABItemPreview() {
    MyWikiDexAppTheme() {
        CardFABItem(
            "Hola mundo",
            painterResource(R.drawable.rounded_delete_24),
            "Borrar",
            {},
            {}
        )
    }
}