package de.syntax_institut.funappsvorlage.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import de.syntax_institut.funappsvorlage.data.datamodels.Meme
@Dao
interface MemeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(c:Meme)

    @Update
    suspend fun update(c:Meme)

    @Query("SELECT * FROM meme_table")
    fun getAll(): LiveData<List<Meme>>

    @Query("SELECT * FROM meme_table WHERE id=:key")
    fun getById(key:String): LiveData<Meme>

    @Query("DELETE FROM meme_table WHERE id = :id")
    suspend fun deleteById(id:Long)

    @Query("DELETE FROM meme_table")
    suspend fun deleteAll()
}

