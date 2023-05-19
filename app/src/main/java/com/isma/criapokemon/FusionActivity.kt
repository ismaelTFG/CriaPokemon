package com.isma.criapokemon

import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.isma.criapokemon.entity.Caja
import com.isma.criapokemon.service.impl.CajaServiceImpl
import com.isma.criapokemon.service.impl.PokedexServiceImpl
import com.isma.criapokemon.service.impl.PokemonServiceImpl
import com.isma.criapokemon.variablesdrawable.ColoresTipos
import com.isma.criapokemon.variablesdrawable.VariablesImgPokemons

class FusionActivity : AppCompatActivity() {

    private val pokemonService = PokemonServiceImpl(this)
    private val cajaService = CajaServiceImpl(this)
    private val pokedexService = PokedexServiceImpl(this)
    private val coloresTipos = ColoresTipos()
    private val variablesImgPokemons = VariablesImgPokemons()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fusion)

        val nombre = findViewById<TextView>(R.id.nombrefusion)
        val pokeimg = findViewById<ImageView>(R.id.poke)
        val id = intent.getStringExtra("fusion").toString()
        val pokemon = pokemonService.findById(id)
        val lista = cajaService.descodificar(intent.getStringExtra("caja").toString())

        cajaService.add(Caja(lista[lista.size-1].id+1, pokemon.name, pokemon))
        pokedexService.visible(pokemon.id)

        nombre.setText(pokemon.name)
        nombre.setBackgroundResource(coloresTipos.colores(pokemon.tipoUno, pokemon.tipoDos))
        pokeimg.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(pokemon.img)))

    }

    fun salir(view: View){

        finish()
        startActivity(Intent(this, MainActivity::class.java))

    }

}