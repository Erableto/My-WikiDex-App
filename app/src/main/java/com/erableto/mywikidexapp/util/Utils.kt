package com.erableto.mywikidexapp.util

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.erableto.mywikidexapp.R
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

fun getReadableTitleFromURL(url: String?): String? {
    if (url != null) {
        val raw = url.removePrefix(WIKIDEX_URL_HEADER)
        return URLDecoder.decode(raw, StandardCharsets.UTF_8.toString()).replace("_", " ")
    } else {
        return null
    }
}

fun Context.vibrateError() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val vibrationEffect = VibrationEffect.createOneShot(
            200,
            VibrationEffect.DEFAULT_AMPLITUDE
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator.vibrate(vibrationEffect)
        } else {
            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(vibrationEffect)
        }
    } else {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(200)
    }
}

@Composable
fun getTypeColor(type: String?): Color {
    if (type != null) {
        when (type) {
            BUG_TYPE -> return colorResource(id = R.color.bug_type)
            DARK_TYPE -> return colorResource(id = R.color.dark_type)
            DRAGON_TYPE -> return colorResource(id = R.color.dragon_type)
            ELECTRIC_TYPE -> return colorResource(id = R.color.electric_type)
            FAIRY_TYPE -> return colorResource(id = R.color.fairy_type)
            FIGHTING_TYPE -> return colorResource(id = R.color.fighting_type)
            FIRE_TYPE -> return colorResource(id = R.color.fire_type)
            FLYING_TYPE -> return colorResource(id = R.color.flying_type)
            GHOST_TYPE -> return colorResource(id = R.color.ghost_type)
            GRASS_TYPE -> return colorResource(id = R.color.grass_type)
            GROUND_TYPE -> return colorResource(id = R.color.ground_type)
            ICE_TYPE -> return colorResource(id = R.color.ice_type)
            NORMAL_TYPE -> return colorResource(id = R.color.normal_type)
            POISON_TYPE -> return colorResource(id = R.color.poison_type)
            PSYCHIC_TYPE -> return colorResource(id = R.color.psychic_type)
            ROCK_TYPE -> return colorResource(id = R.color.rock_type)
            STEEL_TYPE -> return colorResource(id = R.color.steel_type)
            WATER_TYPE -> return colorResource(id = R.color.water_type)
            else -> return MaterialTheme.colorScheme.surfaceContainer
        }
    }

    return MaterialTheme.colorScheme.surfaceContainer
}