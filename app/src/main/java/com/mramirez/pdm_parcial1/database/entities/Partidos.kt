package com.mramirez.pdm_parcial1.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "partidos")
data class Partidos(

    @ColumnInfo(name = "team_A")
    val team_A:String,
    @ColumnInfo(name = "team_B")
    val team_B:String,
    @ColumnInfo(name = "score_team_A")
    val score_team_A:String,
    @ColumnInfo(name = "score_team_B")
    val score_team_B:String,
    @ColumnInfo(name = "date")
    val date:String,
    @ColumnInfo(name = "hour")
    val hour:String,
    @ColumnInfo(name = "winner")
    val winner:String

){
    @PrimaryKey(autoGenerate = true)
    var id:Long=0
}