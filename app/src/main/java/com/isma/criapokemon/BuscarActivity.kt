package com.isma.criapokemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.graphics.BitmapFactory
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import com.isma.criapokemon.entity.Caja
import com.isma.criapokemon.entity.Pokemon
import com.isma.criapokemon.service.impl.CajaServiceImpl
import com.isma.criapokemon.variablesdrawable.VariablesImgPokemons

class BuscarActivity : AppCompatActivity() {

    private var equipo = ArrayList<Caja>()
    private val cajaService = CajaServiceImpl(this)
    private val variablesImgPokemons = VariablesImgPokemons()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar)

        val buscar1 = findViewById<ImageButton>(R.id.buscar1)
        val buscar2 = findViewById<ImageButton>(R.id.buscar2)
        val buscar3 = findViewById<ImageButton>(R.id.buscar3)
        val buscar4 = findViewById<ImageButton>(R.id.buscar4)
        val buscar5 = findViewById<ImageButton>(R.id.buscar5)
        val buscar6 = findViewById<ImageButton>(R.id.buscar6)

        equipo.add(Caja(0, "", Pokemon("", "", "", "", "")))
        equipo.add(Caja(0, "", Pokemon("", "", "", "", "")))
        equipo.add(Caja(0, "", Pokemon("", "", "", "", "")))
        equipo.add(Caja(0, "", Pokemon("", "", "", "", "")))
        equipo.add(Caja(0, "", Pokemon("", "", "", "", "")))
        equipo.add(Caja(0, "", Pokemon("", "", "", "", "")))

        buscar1.setOnClickListener {

            ventana(buscar1, 0)

        }
        buscar2.setOnClickListener {

            ventana(buscar2, 1)

        }
        buscar3.setOnClickListener {

            ventana(buscar3, 2)

        }
        buscar4.setOnClickListener {

            ventana(buscar4, 3)

        }
        buscar5.setOnClickListener {

            ventana(buscar5, 4)

        }
        buscar6.setOnClickListener {

            ventana(buscar6, 5)

        }

    }

    private fun ventana(boton: ImageButton, numero: Int){

        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.spinner_pokemons, null)
        val spinner = dialogLayout.findViewById<Spinner>(R.id.spinnerpokemons)

        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cajaService.viewPokemon())
        builder.setTitle("Elige un pokemon")
        builder.setView(dialogLayout)
        builder.setPositiveButton("Seleccionar"){ dialogInterface, i ->

            val selecionado = cajaService.findByApodo(spinner.selectedItem.toString())
            var rep = false

            for (i in equipo){
                if (i.apodo == selecionado.apodo){

                    rep = true;

                }
            }

            if (!rep){

                val bmp = BitmapFactory.decodeResource(resources, variablesImgPokemons.img(selecionado.pokemon.img))

                equipo[numero] = selecionado
                boton.setImageBitmap(bmp)
                Toast.makeText(this, "Seleccionaste a "+selecionado.apodo, Toast.LENGTH_LONG).show()

            }else{

                Toast.makeText(this, "Ese pokemon ya esta selecionado", Toast.LENGTH_LONG).show()

            }

            dialogInterface.dismiss()

        }
        builder.show()

    }

    fun salir(view: View){

        finish()

    }

}