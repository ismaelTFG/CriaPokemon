package com.isma.criapokemon

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.isma.criapokemon.entity.Caja
import com.isma.criapokemon.entity.Pokemon
import com.isma.criapokemon.service.impl.PokedexServiceImpl
import com.isma.criapokemon.service.impl.PokemonServiceImpl
import com.isma.criapokemon.variablesdrawable.VariablesImgPokemons

class PokedexActivity : AppCompatActivity() {

    private val pokedexService = PokedexServiceImpl(this)
    private val pokemonService = PokemonServiceImpl(this)
    private val variablesImgPokemons = VariablesImgPokemons()
    private var numero = 0
    private var mostrado = Pokemon("", "", "", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)

        val nombre = findViewById<TextView>(R.id.nombre)
        val descripcion = findViewById<TextView>(R.id.descripcionpokedex)
        val pokemon = findViewById<ImageView>(R.id.pokemon)
        val anterior = findViewById<ImageButton>(R.id.anteriorpokedex)
        val siguiente = findViewById<ImageButton>(R.id.siguientepokedex)
        val pokemons = pokemonService.listAll()
        val visible = pokedexService.findAll()


        mostrado = pokemons[numero]

        desbloqueo(nombre, descripcion, pokemon, visible)
        anterior.setOnClickListener {

            if (numero == 0){

                numero = pokemons.size -1
                mostrado = pokemons[numero]
                desbloqueo(nombre, descripcion, pokemon, visible)

            }else{

                numero--
                mostrado = pokemons[numero]
                desbloqueo(nombre, descripcion, pokemon, visible)

            }

        }
        siguiente.setOnClickListener {

            if (numero == (pokemons.size - 1)){

                numero = 0
                mostrado = pokemons[numero]
                desbloqueo(nombre, descripcion, pokemon, visible)

            }else{

                numero++
                mostrado = pokemons[numero]
                desbloqueo(nombre, descripcion, pokemon, visible)

            }

        }

    }

    fun salir(view: View){

        finish()

    }

    private fun desbloqueo(nombre: TextView, descripcion: TextView, pokemon: ImageView, visible: ArrayList<Boolean>){

        if (visible[numero]){

            nombre.setText(mostrado.id+mostrado.name)
            descripcion.setText(mostrado.toString(this, mostrado.id))
            pokemon.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(mostrado.img)))

        }else{

            nombre.setText(mostrado.id+" *****************")
            descripcion.setText("**********************************************************************************************************************************************************************************************************************************************")
            pokemon.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable._0))

        }

    }

}