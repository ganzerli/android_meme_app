package de.syntax_institut.funappsvorlage.data.datamodels

import androidx.room.Entity
import androidx.room.PrimaryKey

// Die Klasse Meme speichert die Infos für ein einzelnes Meme
@Entity("meme_table")
data class Meme(
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0,
    var name: String,
    val url: String
)
