package com.mramirez.pdm_parcial1.database.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.mramirez.pdm_parcial1.database.daos.PartidosDao
import com.mramirez.pdm_parcial1.database.entities.Partidos

class PartidosRepository(private val repoDao: PartidosDao){

    fun getAllPartidos(): LiveData<List<Partidos>> = repoDao.getAllPartidos()

    fun deletePartidos() = repoDao.deletePartidos()

    fun getMatchById(id:Long):LiveData<Partidos> = repoDao.getMatchById(id)

    @WorkerThread
    suspend fun insert(partido: Partidos){
        repoDao.insertPartido(partido)
    }
}