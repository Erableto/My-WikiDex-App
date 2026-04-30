package com.erableto.mywikidexapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.erableto.mywikidexapp.R
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme
import com.erableto.mywikidexapp.util.GENDER_FEMALE
import com.erableto.mywikidexapp.util.GENDER_MALE
import com.erableto.mywikidexapp.util.GENDER_UNKNOWN
import com.erableto.mywikidexapp.util.getGenderName

@Composable
fun PKMNGenderIcon(
    modifier: Modifier = Modifier,
    gender: String?
) {
    val painterResource = when (gender) {
        GENDER_MALE -> painterResource(R.drawable.rounded_male_24)
        GENDER_FEMALE -> painterResource(R.drawable.rounded_female_24)
        GENDER_UNKNOWN -> painterResource(R.drawable.rounded_blank_24)
        else -> painterResource(R.drawable.rounded_blank_24)
    }

    Image(
        modifier = modifier,
        painter = painterResource,
        contentDescription = getGenderName(gender)
    )
}

@Preview(showBackground = true)
@Composable
fun PKMNGenderIconPreview() {
    MyWikiDexAppTheme() {
        PKMNGenderIcon(gender = GENDER_MALE)
    }
}