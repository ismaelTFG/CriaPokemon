package com.isma.criapokemon

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
import com.isma.criapokemon.variablesdrawable.VariablesImgPokemons

class FusionActivity : AppCompatActivity() {

    private val pokemonService = PokemonServiceImpl(this)
    private val cajaService = CajaServiceImpl(this)
    private val pokedexService = PokedexServiceImpl(this)
    private val variablesImgPokemons = VariablesImgPokemons()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fusion)

        val nombre = findViewById<TextView>(R.id.nombrefusion)
        val pokeimg = findViewById<ImageView>(R.id.poke)
        val id = intent.getStringExtra("fusion").toString()
        val pokemon = pokemonService.findById(id)

        cajaService.add(Caja(cajaService.findAll().size+2, pokemon.name, pokemon))
        pokedexService.visible(pokemon.id)

        nombre.setText(pokemon.name)
        pokeimg.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(pokemon.img)))

    }

    fun salir(view: View){

        finish()

    }

}