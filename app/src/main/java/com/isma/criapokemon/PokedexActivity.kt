package com.isma.criapokemon

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import com.isma.criapokemon.entity.Caja
import com.isma.criapokemon.entity.Pokemon
import com.isma.criapokemon.service.impl.PokedexServiceImpl
import com.isma.criapokemon.service.impl.PokemonServiceImpl
import com.isma.criapokemon.variablesdrawable.ColoresTipos
import com.isma.criapokemon.variablesdrawable.VariablesImgPokemons

class PokedexActivity : AppCompatActivity() {

    private val pokedexService = PokedexServiceImpl(this)
    private val pokemonService = PokemonServiceImpl(this)
    private val variablesImgPokemons = VariablesImgPokemons()
    private val coloresTipos = ColoresTipos()
    private var numero = 0
    private var mostrado = Pokemon("", "", "", "", "", "")

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokedex)

        val nombre = findViewById<TextView>(R.id.nombre)
        val descripcion = findViewById<TextView>(R.id.descripcionpokedex)
        val pokemon = findViewById<ImageView>(R.id.pokemon)
        val anterior = findViewById<ImageButton>(R.id.anteriorpokedex)
        val siguiente = findViewById<ImageButton>(R.id.siguientepokedex)
        val filtro = findViewById<Button>(R.id.filtrar)
        var pokemons = pokemonService.listAll()
        var visible = pokedexService.findAll()


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
        filtro.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.spinner_pokemons, null)
            val spinner = dialogLayout.findViewById<Spinner>(R.id.spinnerpokemons)

            spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, pokemonService.findByNoFusion())
            builder.setTitle("Añadir filtro")
            builder.setView(dialogLayout)
            builder.setPositiveButton("Seleccionar"){dialogInterface, a ->

                val seleccionado = spinner.selectedItem.toString()
                val newPokemons = ArrayList<Pokemon>()
                val newVisible = ArrayList<Boolean>()

                for (i in pokemonService.listAll()){

                    val indices = i.id.split("_")

                    if (indices.size != 1){
                        if (indices[1] == seleccionado){

                            newPokemons.add(i)
                            newVisible.add(pokedexService.findById(i.id))

                        }
                    }

                    if (indices[0] == seleccionado){

                        newPokemons.add(i)
                        newVisible.add(pokedexService.findById(i.id))

                    }

                }

                numero = 0
                pokemons = newPokemons
                visible = newVisible
                mostrado = pokemons[numero]
                desbloqueo(nombre, descripcion, pokemon, visible)

                dialogInterface.dismiss()

            }
            builder.show()

        }

    }

    fun salir(view: View){

        finish()

    }

    private fun desbloqueo(nombre: TextView, descripcion: TextView, pokemon: ImageView, visible: ArrayList<Boolean>){

        if (visible[numero]){

            nombre.setText(mostrado.id+" "+mostrado.name)
            nombre.setBackgroundResource(coloresTipos.colores(mostrado.tipoUno, mostrado.tipoDos))
            descripcion.setText(mostrado.toString(this, mostrado.id))
            descripcion.setBackgroundResource(coloresTipos.colores(mostrado.tipoUno, mostrado.tipoDos))
            pokemon.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(mostrado.img)))

        }else{

            nombre.setText(mostrado.id+" *****************")
            nombre.setBackgroundResource(0)
            descripcion.setText("**********************************************************************************************************************************************************************************************************************************************")
            descripcion.setBackgroundResource(0)
            pokemon.setImageBitmap(BitmapFactory.decodeResource(resources, R.drawable._0))

        }

    }

}