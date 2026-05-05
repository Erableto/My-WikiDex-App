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

@Composable
fun getTypePainter(type: String?): Painter {
    if (type != null) {
        when (type) {
            NORMAL_TYPE -> return painterResource(R.drawable.ic_type_normal)
            GRASS_TYPE -> return painterResource(R.drawable.ic_type_grass)
            FIRE_TYPE -> return painterResource(R.drawable.ic_type_fire)
            WATER_TYPE -> return painterResource(R.drawable.ic_type_water)
            ELECTRIC_TYPE -> return painterResource(R.drawable.ic_type_electric)
            BUG_TYPE -> return painterResource(R.drawable.ic_type_bug)
            FLYING_TYPE -> return painterResource(R.drawable.ic_type_flying)
            ROCK_TYPE -> return painterResource(R.drawable.ic_type_rock)
            POISON_TYPE -> return painterResource(R.drawable.ic_type_poison)
            GROUND_TYPE -> return painterResource(R.drawable.ic_type_ground)
            ICE_TYPE -> return painterResource(R.drawable.ic_type_ice)
            FIGHTING_TYPE -> return painterResource(R.drawable.ic_type_fighting)
            PSYCHIC_TYPE -> return painterResource(R.drawable.ic_type_psychic)
            GHOST_TYPE -> return painterResource(R.drawable.ic_type_ghost)
            DRAGON_TYPE -> return painterResource(R.drawable.ic_type_dragon)
            DARK_TYPE -> return painterResource(R.drawable.ic_type_dark)
            STEEL_TYPE -> return painterResource(R.drawable.ic_type_steel)
            FAIRY_TYPE -> return painterResource(R.drawable.ic_type_fairy)
            else -> return painterResource(R.drawable.ic_type_null)
        }
    }

    return painterResource(R.drawable.ic_type_null)
}

fun getTypeName(type: String?): String {
    if (type != null) {
        when (type) {
            NORMAL_TYPE -> return "Normal"
            GRASS_TYPE -> return "Planta"
            FIRE_TYPE -> return "Fuego"
            WATER_TYPE -> return "Agua"
            ELECTRIC_TYPE -> return "Eléctrico"
            BUG_TYPE -> return "Bicho"
            FLYING_TYPE -> return "Volador"
            ROCK_TYPE -> return "Roca"
            POISON_TYPE -> return "Veneno"
            GROUND_TYPE -> return "Tierra"
            ICE_TYPE -> return "Hielo"
            FIGHTING_TYPE -> return "Lucha"
            PSYCHIC_TYPE -> return "Psíquico"
            GHOST_TYPE -> return "Fantasma"
            DRAGON_TYPE -> return "Dragón"
            DARK_TYPE -> return "Siniestro"
            STEEL_TYPE -> return "Acero"
            FAIRY_TYPE -> return "Hada"
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