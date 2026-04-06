package com.erableto.mywikidexapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.data.Favorite
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme

@Composable
fun FavoritesListItem(
    favorite: Favorite,
    onClickFav: () -> Unit,
    onClickDeleteFav: () -> Unit
) {
    CardFABItem(
        favorite.title,
        painterResource(R.drawable.rounded_heart_broken_24),
        "Quitar de favoritos",
        onClickFav,
        onClickDeleteFav
    )
}

@Preview(showBackground = true)
@Composable
fun FavoritesListItemPreview() {
    val favorite = Favorite(0, "", "Hola mundo")

    MyWikiDexAppTheme() {
        FavoritesListItem(
            favorite,
            {},
            {}
        )
    }
}