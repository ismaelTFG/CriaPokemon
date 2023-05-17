package com.isma.criapokemon

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.isma.criapokemon.entity.Caja
import com.isma.criapokemon.service.impl.*
import java.io.OutputStreamWriter


class MainActivity : AppCompatActivity() {

    private val cajaService = CajaServiceImpl(this)
    private val busquedaService = BusquedaServiceImpl(this)
    private val pokedexService = PokedexServiceImpl(this)
    private var lista = ArrayList<Caja>()

    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(android.support.constraint.R.style.Theme_AppCompat_NoActionBar)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lista = cajaService.findAll()

        if (pokedexService.findAll().size == 0){

            pokedexService.add()

        }

        if (lista.size == 0){

            val caja = findViewById<Button>(R.id.caja)
            val criar = findViewById<Button>(R.id.criar)
            val pokedex = findViewById<Button>(R.id.pokedex)
            val objetos = findViewById<Button>(R.id.objeto)

            caja.visibility = View.GONE
            criar.visibility = View.GONE
            pokedex.visibility = View.GONE
            objetos.visibility = View.GONE

        }

        if (lista.size == 1){

            val criar = findViewById<Button>(R.id.criar)

            criar.visibility = View.GONE

        }

        for (i in lista){
            if (i.pokemon.id == ""){

                cajaService.delete(i.id)

            }
        }

    }

    fun caja(view: View){

        startActivity(Intent(this, PokemonActivity::class.java))

    }

    fun criar(view: View){

        startActivity(Intent(this, CriarActivity::class.java))

    }

    fun buscar(view: View){

        if (lista.size == 0){

            finish()
            startActivity(Intent(this, InicialesActivity::class.java))

        }else if (busquedaService.buscando()){

            startActivity(Intent(this, RecompensasActivity::class.java))

        }else{

            startActivity(Intent(this, BuscarActivity::class.java))

        }

    }

    fun pokedex(view: View){

        startActivity(Intent(this, PokedexActivity::class.java))

    }

    fun objeto(view: View){

        startActivity(Intent(this, ObjetosActivity::class.java))

    }

    fun regalo(view: View){

        startActivity(Intent(this, RegaloActivity::class.java))

    }

}