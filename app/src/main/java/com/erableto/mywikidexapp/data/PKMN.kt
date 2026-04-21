package com.erableto.mywikidexapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erableto.mywikidexapp.util.GENDER_UNKNOWN

@Entity(tableName = "pkmn")
data class PKMN(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
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