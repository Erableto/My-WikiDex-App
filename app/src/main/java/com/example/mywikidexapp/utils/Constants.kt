package com.example.mywikidexapp.utils

import java.net.URLDecoder
import java.nio.charset.StandardCharsets

val WikiDexURL: String = "https://www.wikidex.net/"
val WikiDexURLHeader: String = "https://www.wikidex.net/wiki/"
val WikiDexPortadaURL: String = "https://www.wikidex.net/wiki/WikiDex"
val WikiDexMainDomain: String = "wikidex.net"
val WikiDexMastodonDomain: String = "social.wikidex.net"
val WikiDexACDomain: String = "animalcrossing.wikidex.net"
val WikiDexLabel: String = " - WikiDex, la enciclopedia Pokémon"

fun extractReadableTitleFromURL(url: String?): String? {
    if (url != null) {
        val raw = url.removePrefix(WikiDexURLHeader)
        return URLDecoder.decode(raw, StandardCharsets.UTF_8.toString()).replace("_", " ")
    } else {
        return null
    }
}