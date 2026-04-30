package com.erableto.mywikidexapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
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