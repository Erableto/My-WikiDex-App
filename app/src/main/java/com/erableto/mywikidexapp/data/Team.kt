package com.erableto.mywikidexapp.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erableto.mywikidexapp.model.PKMN

@Entity(tableName = "teams")
data class Team(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String = "Equipo $id",
    @Embedded(prefix = "pkmn1_") val pkmn1: PKMN? = null,
    @Embedded(prefix = "pkmn2_") val pkmn2: PKMN? = null,
    @Embedded(prefix = "pkmn3_") val pkmn3: PKMN? = null,
    @Embedded(prefix = "pkmn4_") val pkmn4: PKMN? = null,
    @Embedded(prefix = "pkmn5_") val pkmn5: PKMN? = null,
    @Embedded(prefix = "pkmn6_") val pkmn6: PKMN? = null
)
