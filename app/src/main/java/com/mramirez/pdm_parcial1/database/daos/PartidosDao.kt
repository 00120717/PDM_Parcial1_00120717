package com.mramirez.pdm_parcial1.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mramirez.pdm_parcial1.database.entities.Partidos

@Dao
interface PartidosDao{

    @Query("SELECT * FROM partidos")
    fun getAllPartidos(): LiveData<List<Partidos>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPartido(repo: Partidos)

    @Query("DELETE FROM partidos")
    fun deletePartidos()
}