package com.erableto.mywikidexapp.util

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
            NORMAL_TYPE -> return colorResource(id = R.color.normal_type)
            GRASS_TYPE -> return colorResource(id = R.color.grass_type)
            FIRE_TYPE -> return colorResource(id = R.color.fire_type)
            WATER_TYPE -> return colorResource(id = R.color.water_type)
            ELECTRIC_TYPE -> return colorResource(id = R.color.electric_type)
            BUG_TYPE -> return colorResource(id = R.color.bug_type)
            FLYING_TYPE -> return colorResource(id = R.color.flying_type)
            ROCK_TYPE -> return colorResource(id = R.color.rock_type)
            POISON_TYPE -> return colorResource(id = R.color.poison_type)
            GROUND_TYPE -> return colorResource(id = R.color.ground_type)
            ICE_TYPE -> return colorResource(id = R.color.ice_type)
            FIGHTING_TYPE -> return colorResource(id = R.color.fighting_type)
            PSYCHIC_TYPE -> return colorResource(id = R.color.psychic_type)
            GHOST_TYPE -> return colorResource(id = R.color.ghost_type)
            DRAGON_TYPE -> return colorResource(id = R.color.dragon_type)
            DARK_TYPE -> return colorResource(id = R.color.dark_type)
            STEEL_TYPE -> return colorResource(id = R.color.steel_type)
            FAIRY_TYPE -> return colorResource(id = R.color.fairy_type)
            else -> return MaterialTheme.colorScheme.surfaceContainer
        }
    }

    return MaterialTheme.colorScheme.surfaceContainer
}

fun getTypeName(type: String?): String {
    if (type != null) {
        when (type) {
            NORMAL_TYPE -> return "Tipo Normal"
            GRASS_TYPE -> return "Tipo Planta"
            FIRE_TYPE -> return "Tipo Fuego"
            WATER_TYPE -> return "Tipo Agua"
            ELECTRIC_TYPE -> return "Tipo Eléctrico"
            BUG_TYPE -> return "Tipo Bicho"
            FLYING_TYPE -> return "Tipo Volador"
            ROCK_TYPE -> return "Tipo Roca"
            POISON_TYPE -> return "Tipo Veneno"
            GROUND_TYPE -> return "Tipo Tierra"
            ICE_TYPE -> return "Tipo Hielo"
            FIGHTING_TYPE -> return "Tipo Lucha"
            PSYCHIC_TYPE -> return "Tipo Psíquico"
            GHOST_TYPE -> return "Tipo Fantasma"
            DRAGON_TYPE -> return "Tipo Dragón"
            DARK_TYPE -> return "Tipo Siniestro"
            STEEL_TYPE -> return "Tipo Acero"
            FAIRY_TYPE -> return "Tipo Hada"
            else -> return "-"
        }
    }

    return "-"
}

fun getGenderName(gender: String?): String {
    if (gender != null) {
        when (gender) {
            GENDER_UNKNOWN -> return "Desconocido"
            GENDER_MALE -> return "Macho"
            GENDER_FEMALE -> return "Hembra"
            else -> return "Desconocido"
        }
    }

    return "Desconocido"
}