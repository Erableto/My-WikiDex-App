package com.erableto.mywikidexapp.util

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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

@Suppress("DEPRECATION")
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
        return when (type) {
            NORMAL_TYPE -> colorResource(id = R.color.normal_type)
            GRASS_TYPE -> colorResource(id = R.color.grass_type)
            FIRE_TYPE -> colorResource(id = R.color.fire_type)
            WATER_TYPE -> colorResource(id = R.color.water_type)
            ELECTRIC_TYPE -> colorResource(id = R.color.electric_type)
            BUG_TYPE -> colorResource(id = R.color.bug_type)
            FLYING_TYPE -> colorResource(id = R.color.flying_type)
            ROCK_TYPE -> colorResource(id = R.color.rock_type)
            POISON_TYPE -> colorResource(id = R.color.poison_type)
            GROUND_TYPE -> colorResource(id = R.color.ground_type)
            ICE_TYPE -> colorResource(id = R.color.ice_type)
            FIGHTING_TYPE -> colorResource(id = R.color.fighting_type)
            PSYCHIC_TYPE -> colorResource(id = R.color.psychic_type)
            GHOST_TYPE -> colorResource(id = R.color.ghost_type)
            DRAGON_TYPE -> colorResource(id = R.color.dragon_type)
            DARK_TYPE -> colorResource(id = R.color.dark_type)
            STEEL_TYPE -> colorResource(id = R.color.steel_type)
            FAIRY_TYPE -> colorResource(id = R.color.fairy_type)
            else -> MaterialTheme.colorScheme.surfaceContainer
        }
    }

    return MaterialTheme.colorScheme.surfaceContainer
}

@Composable
fun getTypePainter(type: String?): Painter {
    if (type != null) {
        return when (type) {
            NORMAL_TYPE -> painterResource(R.drawable.ic_type_normal)
            GRASS_TYPE -> painterResource(R.drawable.ic_type_grass)
            FIRE_TYPE -> painterResource(R.drawable.ic_type_fire)
            WATER_TYPE -> painterResource(R.drawable.ic_type_water)
            ELECTRIC_TYPE -> painterResource(R.drawable.ic_type_electric)
            BUG_TYPE -> painterResource(R.drawable.ic_type_bug)
            FLYING_TYPE -> painterResource(R.drawable.ic_type_flying)
            ROCK_TYPE -> painterResource(R.drawable.ic_type_rock)
            POISON_TYPE -> painterResource(R.drawable.ic_type_poison)
            GROUND_TYPE -> painterResource(R.drawable.ic_type_ground)
            ICE_TYPE -> painterResource(R.drawable.ic_type_ice)
            FIGHTING_TYPE -> painterResource(R.drawable.ic_type_fighting)
            PSYCHIC_TYPE -> painterResource(R.drawable.ic_type_psychic)
            GHOST_TYPE -> painterResource(R.drawable.ic_type_ghost)
            DRAGON_TYPE -> painterResource(R.drawable.ic_type_dragon)
            DARK_TYPE -> painterResource(R.drawable.ic_type_dark)
            STEEL_TYPE -> painterResource(R.drawable.ic_type_steel)
            FAIRY_TYPE -> painterResource(R.drawable.ic_type_fairy)
            else -> painterResource(R.drawable.ic_type_null)
        }
    }

    return painterResource(R.drawable.ic_type_null)
}

fun getTypeName(type: String?): String {
    if (type != null) {
        return when (type) {
            NORMAL_TYPE -> "Normal"
            GRASS_TYPE -> "Planta"
            FIRE_TYPE -> "Fuego"
            WATER_TYPE -> "Agua"
            ELECTRIC_TYPE -> "Eléctrico"
            BUG_TYPE -> "Bicho"
            FLYING_TYPE -> "Volador"
            ROCK_TYPE -> "Roca"
            POISON_TYPE -> "Veneno"
            GROUND_TYPE -> "Tierra"
            ICE_TYPE -> "Hielo"
            FIGHTING_TYPE -> "Lucha"
            PSYCHIC_TYPE -> "Psíquico"
            GHOST_TYPE -> "Fantasma"
            DRAGON_TYPE -> "Dragón"
            DARK_TYPE -> "Siniestro"
            STEEL_TYPE -> "Acero"
            FAIRY_TYPE -> "Hada"
            else -> "-"
        }
    }

    return "-"
}

fun getGenderName(gender: String?): String {
    if (gender != null) {
        return when (gender) {
            GENDER_UNKNOWN -> "Desconocido"
            GENDER_MALE -> "Macho"
            GENDER_FEMALE -> "Hembra"
            else -> "Desconocido"
        }
    }

    return "Desconocido"
}