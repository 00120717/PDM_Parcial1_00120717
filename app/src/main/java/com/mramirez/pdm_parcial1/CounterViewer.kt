package com.mramirez.pdm_parcial1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mramirez.pdm_parcial1.adapters.PartidoAdapter
import com.mramirez.pdm_parcial1.database.entities.Partidos
import com.mramirez.pdm_parcial1.database.viewmodels.PartidosViewmodel
import com.mramirez.pdm_parcial1.database.viewmodels.ScoreViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.viewer_counter.*

class CounterViewer : AppCompatActivity() {

    lateinit var scoreViewModel: ScoreViewModel
    lateinit var adapter: PartidoAdapter
    lateinit var viewModel: PartidosViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewer_counter)

        btn_addInfo.setOnClickListener{
            tv_team_A.text=et_team_A.text.toString()
            tv_team_B.text=et_team_B.text.toString()
        }

        scoreViewModel = ViewModelProviders.of(this)
            .get(ScoreViewModel::class.java)

        displayScore(
            tv_score_team_a,
            scoreViewModel.scoreTeamA
        )

        displayScore(
            tv_score_team_b,
            scoreViewModel.scoreTeamB
        )

        displayWinner(
            tv_winner,
            scoreViewModel.winner
        )

    }

//TODO funcion para insertar partido a la bd
    fun bind(){
        adapter= PartidoAdapter(ArrayList())
        viewModel= ViewModelProviders.of(this).get(PartidosViewmodel::class.java)

        rv_partidos.apply {
            adapter=this@CounterViewer.adapter
            layoutManager= LinearLayoutManager(this@CounterViewer)
        }

        viewModel.getAllPartidos().observe(this, Observer {
            adapter.updateList(it)
        })

        btn_finish.setOnClickListener{


            //TODO insertar partido al dar click al boton Finish
            viewModel.insertPartidos(
                Partidos(
                    tv_team_A.text.toString(),
                    tv_team_B.text.toString(),
                    tv_score_team_a.text.toString(),
                    tv_score_team_b.text.toString(),
                    et_date.text.toString(),
                    et_time.text.toString(),
                    tv_winner.text.toString()
                    ))

            startActivity(Intent(this, MainActivity::class.java))
        }


    }

    fun winner(v: View){
        if(scoreViewModel.scoreTeamA>scoreViewModel.scoreTeamB){
            scoreViewModel.winner = et_team_A.text.toString()
        }else{
            scoreViewModel.winner = et_team_B.text.toString()
        }

        displayWinner(
            tv_winner,
            scoreViewModel.winner
        )
    }


    //TODO mostrar ganador
    fun displayWinner(v: TextView, winner: String) {
        v.text = winner
    }


    fun addOneTeamA(v: View) {
        displayScore(
            tv_score_team_a,
            ++scoreViewModel.scoreTeamA
        )
    }

    fun addOneTeamB(v: View) {
        displayScore(
            tv_score_team_b,
            ++scoreViewModel.scoreTeamB
        )
    }

    fun addTwoTeamA(v: View) {
        scoreViewModel.scoreTeamA += 2
        displayScore(
            tv_score_team_a,
            scoreViewModel.scoreTeamA
        )
    }

    fun addTwoTeamB(v: View) {
        scoreViewModel.scoreTeamB += 2
        displayScore(
            tv_score_team_b,
            scoreViewModel.scoreTeamB
        )
    }

    fun addThreeTeamA(v: View) {
        scoreViewModel.scoreTeamA += 3
        displayScore(
            tv_score_team_a,
            scoreViewModel.scoreTeamA
        )
    }

    fun addThreeTeamB(v: View) {
        scoreViewModel.scoreTeamB += 3
        displayScore(
            tv_score_team_b,
            scoreViewModel.scoreTeamB
        )
    }

    // TODO: Limpiando datos
    fun resetScores(v: View) {
        scoreViewModel.scoreTeamA = 0
        scoreViewModel.scoreTeamB = 0

        displayScore(
            tv_score_team_a,
            scoreViewModel.scoreTeamA
        )

        displayScore(
            tv_score_team_b,
            scoreViewModel.scoreTeamB
        )
    }

    fun displayScore(v: TextView, score: Int) {
        v.text = score.toString()
        winner(v)
    }

}