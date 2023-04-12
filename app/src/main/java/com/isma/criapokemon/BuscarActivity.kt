package com.isma.criapokemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import com.isma.criapokemon.service.impl.CajaServiceImpl

class BuscarActivity : AppCompatActivity() {

    private var equipo = 0
    private var cajaService = CajaServiceImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar)

        val buscar1 = findViewById<ImageButton>(R.id.buscar1)
        val buscar2 = findViewById<ImageButton>(R.id.buscar2)
        val buscar3 = findViewById<ImageButton>(R.id.buscar3)
        val buscar4 = findViewById<ImageButton>(R.id.buscar4)
        val buscar5 = findViewById<ImageButton>(R.id.buscar5)
        val buscar6 = findViewById<ImageButton>(R.id.buscar6)

        buscar1.setOnClickListener {

            ventana()

        }

    }

    private fun ventana(){

        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.spinner_pokemons, null)
        val spinner = dialogLayout.findViewById<Spinner>(R.id.spinnerpokemons)
        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cajaService.viewPokemon())
        builder.setTitle("Elige un pokemon")
        builder.setView(dialogLayout)
        builder.setPositiveButton("Seleccionar"){ dialogInterface, i ->



        }

    }

}