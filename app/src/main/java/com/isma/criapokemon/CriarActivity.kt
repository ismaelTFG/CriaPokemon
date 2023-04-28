package com.isma.criapokemon

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.*
import com.isma.criapokemon.entity.Caja
import com.isma.criapokemon.entity.Pokemon
import com.isma.criapokemon.service.impl.CajaServiceImpl
import com.isma.criapokemon.service.impl.HuevoServiceImpl
import com.isma.criapokemon.variablesdrawable.CriasPokemons
import com.isma.criapokemon.variablesdrawable.VariablesImgPokemons

class CriarActivity : AppCompatActivity() {

    private val cajaService = CajaServiceImpl(this)
    private val huevoService = HuevoServiceImpl(this)
    private val variablesImgPokemons = VariablesImgPokemons()
    private val criasPokemons = CriasPokemons()
    private var mostrar = ArrayList<Caja>()
    private var cajaUno = Caja(0, "", Pokemon("", "", "_0", "", "", ""))
    private var cajados = Caja(0, "", Pokemon("", "", "_0", "", "", ""))

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criar)

        val huevo = findViewById<Spinner>(R.id.huevo)
        val buscar = findViewById<Button>(R.id.buscarcria)
        val criar = findViewById<Button>(R.id.criarcriar)
        val pokeuno = findViewById<ImageButton>(R.id.primer)
        val pokedos = findViewById<ImageButton>(R.id.segundo)
        val lista = cajaService.findAll()
        var pokes = ArrayList<String>()
        var criarTrue = false

        mostrar = lista
        img(mostrar[0].pokemon, pokeuno)
        img(mostrar[1].pokemon, pokedos)

        huevo.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, huevoService.viewHuevos(lista))
        buscar.setOnClickListener {

            val new = ArrayList<Caja>()

            criarTrue = true
            pokes = huevoService.listAllPokes(huevo.selectedItem.toString())

            for (i in lista){
                for (j in pokes){
                    if (i.pokemon.id == j){

                        new.add(i)

                    }
                }
            }

            mostrar = new
            img(mostrar[0].pokemon, pokeuno)
            cajaUno = mostrar[0]

            if (mostrar.size > 1){

                img(mostrar[1].pokemon, pokedos)
                cajados = mostrar[1]

            }else{

                img(mostrar[0].pokemon, pokedos)
                cajados = mostrar[0]

            }

        }
        pokeuno.setOnClickListener {

            ventana(pokeuno, 1)

        }
        pokedos.setOnClickListener {

            ventana(pokedos, 2)

        }
        criar.setOnClickListener {

            if (criarTrue){

                criarPokes(pokes)

            }else{

                Toast.makeText(this, "seleccione primero el grupo huevo", Toast.LENGTH_LONG).show()

            }

        }

    }

    fun salir(view: View){

        finish()

    }

    private fun img(pokemon: Pokemon, boton: ImageButton){

        val bmp = BitmapFactory.decodeResource(resources, variablesImgPokemons.img(pokemon.img))

        boton.setImageBitmap(bmp)

    }
    private fun ventana(boton: ImageButton, caja: Int){

        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.spinner_pokemons, null)
        val spinner = dialogLayout.findViewById<Spinner>(R.id.spinnerpokemons)

        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, mostrarView(mostrar))
        builder.setTitle("Elige un pokemon")
        builder.setView(dialogLayout)
        builder.setPositiveButton("Seleccionar"){ dialogInterface, i ->

            val seleccionado = cajaService.findByApodo(spinner.selectedItem.toString())

            if (caja == 1){

                cajaUno = seleccionado
                Toast.makeText(this, "Seleccionaste a "+seleccionado.apodo, Toast.LENGTH_LONG).show()
                img(cajaUno.pokemon, boton)
                dialogInterface.dismiss()

            }else{

                cajados = seleccionado
                Toast.makeText(this, "Seleccionaste a "+seleccionado.apodo, Toast.LENGTH_LONG).show()
                img(cajados.pokemon, boton)
                dialogInterface.dismiss()

            }

        }
        builder.show()

    }
    private fun mostrarView(mostrar: ArrayList<Caja>): ArrayList<String>{

        val lista = ArrayList<String>()

        for (i in mostrar){

            lista.add(i.apodo)

        }

        return lista

    }
    private fun criarPokes(pokes: ArrayList<String>){

        if (cajaUno.id != cajados.id){
            if (cajaUno.pokemon.id != cajados.pokemon.id){

                var primerTrue = false
                var segundoTrue = false

                for (i in pokes){

                    if (i == cajaUno.pokemon.id){

                        primerTrue = true

                    }
                    if (i == cajados.pokemon.id){

                        segundoTrue = true

                    }

                }

                if (primerTrue == segundoTrue){

                    val i = Intent(this, FusionActivity::class.java)
                    var id = ""

                    if (cajaUno.pokemon.id < cajados.pokemon.id){

                        id = criasPokemons.crias(cajaUno.pokemon.id)+"_"+criasPokemons.crias(cajados.pokemon.id)

                    }else{

                        id = criasPokemons.crias(cajados.pokemon.id)+"_"+criasPokemons.crias(cajaUno.pokemon.id)

                    }

                    i.putExtra("fusion", id)

                    finish()
                    startActivity(i)

                }else{

                    Toast.makeText(this, "estos pokemon no son compatibles", Toast.LENGTH_LONG).show()

                }

            }else{

                val i = Intent(this, FusionActivity::class.java)

                i.putExtra("fusion", criasPokemons.crias(cajaUno.pokemon.id))

                finish()
                startActivity(i)

            }
        }else{

            Toast.makeText(this, "no puede criar el mismo pokemon", Toast.LENGTH_LONG).show()

        }

    }

}