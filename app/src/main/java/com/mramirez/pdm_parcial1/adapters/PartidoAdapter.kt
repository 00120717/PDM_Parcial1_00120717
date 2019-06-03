package com.mramirez.pdm_parcial1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mramirez.pdm_parcial1.R
import com.mramirez.pdm_parcial1.database.entities.Partidos
import kotlinx.android.synthetic.main.cv_match.view.*
import kotlinx.android.synthetic.main.cv_match.view.*

class PartidoAdapter(var partidos:List<Partidos>):RecyclerView.Adapter<PartidoAdapter.ViewHolder>(){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view= LayoutInflater.from(p0.context).inflate(R.layout.cv_match,p0,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return partidos.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(partidos[p1])
    }

    fun updateList(newPartidos:List<Partidos>){
        this.partidos=newPartidos
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(partidos:Partidos)= with(itemView){
            cv_team_A.text=partidos.team_A
            cv_team_B.text=partidos.team_B
            cv_score_A.text=partidos.score_team_A
            cv_score_B.text=partidos.score_team_B
            cv_time.text=partidos.hour
            cv_date.text = partidos.date
            cv_winner.text=partidos.winner
        }
    }
}