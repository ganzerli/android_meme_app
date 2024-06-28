package de.syntax_institut.funappsvorlage.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import de.syntax_institut.funappsvorlage.data.datamodels.Meme
import de.syntax_institut.funappsvorlage.data.local.MemeDatabase
import de.syntax_institut.funappsvorlage.data.remote.MemeApi
import java.lang.Exception

const val TAG = "AppRepositoryTAG"

class AppRepository(private val api: MemeApi , private val database: MemeDatabase) {

    // Die LiveData Variable memes enthält die Liste aus dem API call
    private val _memes = MutableLiveData<List<Meme>>()
    val memes: LiveData<List<Meme>>
        get() = _memes



    // getMemes uft die Daten aus dem API Service ab und speichert die Antwort in der Variable _memes
    suspend fun getMemes() {
        Log.e(TAG, "get Memes")
        try {
            val memeData = api.retrofitService.getMemes()
            memeData.data.memes.forEach{ meme -> database.memeDao.insert(meme) }
            _memes.value = memeData.data.memes.shuffled() // Die Memes werden mit shuffled() gemischt
        } catch (e: Exception) {
            Log.e(TAG, "Error loading Data from API: $e")
        }
    }

    suspend fun deleteMeme(m:Meme) {
        Log.e(TAG, "delete one meme")
        try {
            database.memeDao.deleteById(m.id)
            _memes.value = database.memeDao.getAll().value
        } catch (e: Exception) {
            Log.e(TAG, "Error loading Data from API: $e")
        }
    }

    suspend fun deleteALlMemes() {
        Log.e(TAG, "deleteALlMemes")
        try {
            database.memeDao.deleteAll()
            _memes.value = database.memeDao.getAll().value
        } catch (e: Exception) {
            Log.e(TAG, "Error loading Data from API: $e")
        }
    }

    suspend fun updateMeme(m:Meme) {
        Log.e(TAG, "update meme")
        try {
            database.memeDao.deleteById(m.id)
            _memes.value = database.memeDao.getAll().value
        } catch (e: Exception) {
            Log.e(TAG, "Error loading Data from API: $e")
        }
    }




}
