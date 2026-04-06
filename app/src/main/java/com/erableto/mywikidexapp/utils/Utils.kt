package com.erableto.mywikidexapp.utils

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