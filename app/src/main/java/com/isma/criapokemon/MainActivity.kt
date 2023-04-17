package com.isma.criapokemon

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.isma.criapokemon.service.impl.*


class MainActivity : AppCompatActivity() {

    private val pokemonService = PokemonServiceImpl(this)
    private val cajaService = CajaServiceImpl(this)
    private val busquedaService = BusquedaServiceImpl(this)
    private val recompensasService = RecompensasServiceImpl(this)
    private val pokedexService = PokedexServiceImpl(this)
    private val objetoService = ObjetoServiceImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (pokemonService.listAll().size == 0){

            pokemonService.add()

        }

        if (pokedexService.findAll().size == 0){

            val pokemon = pokemonService.listAll()

            for (i in pokemon){

                pokedexService.add(i.id)

            }

        }

        if (recompensasService.listAll().size == 0){

            recompensasService.add()

        }

        if (objetoService.listAll().size == 0){

            objetoService.add()

        }

        if (cajaService.findAll().size == 0){

            val caja = findViewById<Button>(R.id.caja)
            val criar = findViewById<Button>(R.id.criar)
            val pokedex = findViewById<Button>(R.id.pokedex)
            val objetos = findViewById<Button>(R.id.objeto)

            caja.visibility = View.GONE
            criar.visibility = View.GONE
            pokedex.visibility = View.GONE
            objetos.visibility = View.GONE

        }

    }

    fun caja(view: View){

        startActivity(Intent(this, CajaActivity::class.java))

    }

    fun criar(view: View){

        startActivity(Intent(this, CriarActivity::class.java))

    }

    fun buscar(view: View){

        if (cajaService.findAll().size == 0){

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

}