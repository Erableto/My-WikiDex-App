package com.erableto.mywikidexapp.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

fun getReadableTitleFromURL(url: String?): String? {
    if (url != null) {
        val raw = url.removePrefix(WikiDexURLHeader)
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