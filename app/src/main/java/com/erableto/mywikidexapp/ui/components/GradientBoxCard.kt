package com.erableto.mywikidexapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.erableto.mywikidexapp.ui.theme.MyWikiDexAppTheme

@Composable
fun GradientBoxCard(
    modifier: Modifier,
    shape: Shape = CardDefaults.shape,
    colors: List<Color>,
    padding: PaddingValues = PaddingValues(0.dp),
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(shape)
            .background(
                Brush.horizontalGradient(
                    colors = colors
                )
            )
            .padding(padding),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun GradientBoxCardPreview() {
    MyWikiDexAppTheme() {
        GradientBoxCard(
            modifier = Modifier,
            colors = listOf(
                Color.Red,
                Color.Yellow,
                Color.Green,
                Color.Cyan,
                Color.Blue,
                Color.Magenta
            ),
            padding = PaddingValues(16.dp)
        ) {}
    }
}