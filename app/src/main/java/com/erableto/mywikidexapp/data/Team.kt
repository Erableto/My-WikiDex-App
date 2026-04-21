package com.erableto.mywikidexapp.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "team")
data class Team(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val teamName: String = "Equipo $id",
    @Embedded(prefix = "pkmn1_") val pkmn1: PKMN?,
    @Embedded(prefix = "pkmn2_") val pkmn2: PKMN?,
    @Embedded(prefix = "pkmn3_") val pkmn3: PKMN?,
    @Embedded(prefix = "pkmn4_") val pkmn4: PKMN?,
    @Embedded(prefix = "pkmn5_") val pkmn5: PKMN?,
    @Embedded(prefix = "pkmn6_") val pkmn6: PKMN?
)
