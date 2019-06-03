package com.mramirez.pdm_parcial1

import android.content.AbstractThreadedSyncAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mramirez.pdm_parcial1.adapters.PartidoAdapter
import com.mramirez.pdm_parcial1.database.entities.Partidos
import com.mramirez.pdm_parcial1.database.viewmodels.PartidosViewmodel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var partidosViewmodel: PartidosViewmodel
    private lateinit var viewAdapter: PartidoAdapter
    private lateinit var viewManager:RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_new_match.setOnClickListener{
            val intent = Intent(this,CounterViewer::class.java)
            startActivity(intent)
        }

        initRecycler(emptyList())



        partidosViewmodel = ViewModelProviders.of(this).get(PartidosViewmodel::class.java)

        partidosViewmodel.getAllPartidos().observe(this, Observer {
            viewAdapter.updateList(it)
        })
    }

    fun initRecycler(partidos:List<Partidos>){
        viewManager = LinearLayoutManager(this)

        viewAdapter = PartidoAdapter(partidos)

        rv_partidos.apply {
            setHasFixedSize(true)
            layoutManager=viewManager
            adapter = viewAdapter
        }
    }



}
