package de.syntax_institut.funappsvorlage.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.syntax_institut.funappsvorlage.data.datamodels.Meme

@Database(entities = [Meme::class], version = 1)
abstract class MemeDatabase : RoomDatabase() {

    abstract val memeDao: MemeDao

    companion object {
        private lateinit var dbInstance: MemeDatabase
    }
}

private lateinit var INSTANCE:MemeDatabase

fun getDatabase(ctx: Context):MemeDatabase{
    synchronized(MemeDatabase::class.java){
        if(!::INSTANCE.isInitialized){
            INSTANCE = Room.databaseBuilder(
                ctx.applicationContext,
                MemeDatabase::class.java,
                "meme_database"
            ).build()
        }
        return INSTANCE
    }
}