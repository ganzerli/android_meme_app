package de.syntax_institut.funappsvorlage.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import de.syntax_institut.funappsvorlage.data.AppRepository
import de.syntax_institut.funappsvorlage.data.local.getDatabase
import de.syntax_institut.funappsvorlage.data.remote.MemeApi
import kotlinx.coroutines.launch

class MemesViewModel(application: Application) : AndroidViewModel(application) {

    // hier wird eine AppRepository Instanz erstellt, mit dem Parameter MemeApi
    //private val repository = AppRepository(MemeApi)

    private val database = getDatabase(application)
    private val repo = AppRepository(MemeApi,database)

    // hier werden die memes aus dem repository in einer eigenen Variablen gespeichert
    val memes = repo.memes

    // Diese Funktion ruft die Repository-Funktion zum Laden der Memes innerhalb einer Coroutine auf
    fun loadData() {
        viewModelScope.launch {
            repo.getMemes()
        }
    }
}