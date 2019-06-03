package com.mramirez.pdm_parcial1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mramirez.pdm_parcial1.database.daos.PartidosDao
import com.mramirez.pdm_parcial1.database.entities.Partidos


@Database(entities = [Partidos::class],version = 1,exportSchema = false)
public abstract class RoomDB: RoomDatabase(){
    abstract fun partidosDAO():PartidosDao

    //creacion DB y vinculacion Entity
    companion object {
        @Volatile
        private var INSTANCE:RoomDB?=null

        fun getInstance(
            context: Context
        ):RoomDB{
            val tempInstnace = INSTANCE
            if (tempInstnace!=null){
                return tempInstnace
            }
            synchronized(this){
                val instance = Room
                    .databaseBuilder(context,RoomDB::class.java,"partidos_DB")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}