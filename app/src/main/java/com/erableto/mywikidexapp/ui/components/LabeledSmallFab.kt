package com.erableto.mywikidexapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme

@Composable
fun LabeledSmallFab(
    text: String,
    onClick: () -> Unit,
    icon: @Composable () -> Unit
) {
    Row(
        modifier = Modifier.padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .padding(end = 8.dp)
                .clickable {
                    onClick()
                }
        ) {
            Text(
                text,
                modifier = Modifier.padding(8.dp)
            )
        }

        SmallFloatingActionButton(onClick = onClick) {
            icon()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LabeledSmallFabPreview() {
    MyWikiDexAppTheme() {
        LabeledSmallFab(
            "Hola mundo",
            {},
            {
                Icon(
                    painter = painterResource(R.drawable.rounded_share_24),
                    contentDescription = "Compartir"
                )
            }
        )
    }
}