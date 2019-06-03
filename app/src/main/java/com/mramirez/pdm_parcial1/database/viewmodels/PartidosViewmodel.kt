package com.mramirez.pdm_parcial1.database.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mramirez.pdm_parcial1.database.RoomDB
import com.mramirez.pdm_parcial1.database.entities.Partidos
import com.mramirez.pdm_parcial1.database.repositories.PartidosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PartidosViewmodel(app: Application): AndroidViewModel(app){
    //Comunicar a la capa de repository
    private val repository:PartidosRepository

    init {
        val partidosDao = RoomDB.getInstance(app).partidosDAO()
        repository= PartidosRepository(partidosDao)
    }

    fun getAllPartidos(): LiveData<List<Partidos>> = repository.getAllPartidos()

    fun insertPartidos(partido: Partidos) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(partido)
    }
}