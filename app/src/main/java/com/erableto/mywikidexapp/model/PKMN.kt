package com.erableto.mywikidexapp.model

import com.erableto.mywikidexapp.util.GENDER_UNKNOWN

data class PKMN(
    val pkmnName: String = "MissingNo.",
    val itemName: String?,
    val pkmnIcon: String?,
    val itemIcon: String?,
    val type1: String,
    val type2: String?,
    val gender: String = GENDER_UNKNOWN,
    val lv: Int = 0,
    val ability: String = "-",
    val mov1: String = "-",
    val mov2: String = "-",
    val mov3: String = "-",
    val mov4: String = "-"
)