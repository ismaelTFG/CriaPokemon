package com.isma.criapokemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import com.isma.criapokemon.entity.Caja
import com.isma.criapokemon.entity.Pokemon
import com.isma.criapokemon.service.impl.BusquedaServiceImpl
import com.isma.criapokemon.service.impl.CajaServiceImpl
import com.isma.criapokemon.service.impl.EquipoServiceImpl
import com.isma.criapokemon.variablesdrawable.ColoresTipos
import com.isma.criapokemon.variablesdrawable.VariablesImgPokemons
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BuscarActivity : AppCompatActivity() {

    private var equipo = ArrayList<Caja>()
    private val cajaService = CajaServiceImpl(this)
    private val equipoService = EquipoServiceImpl(this)
    private val busquedaService = BusquedaServiceImpl(this)
    private val variablesImgPokemons = VariablesImgPokemons()
    private val coloresTipos = ColoresTipos()
    private var caja = ArrayList<Caja>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar)

        val buscar1 = findViewById<ImageButton>(R.id.buscar1)
        val buscar2 = findViewById<ImageButton>(R.id.buscar2)
        val buscar3 = findViewById<ImageButton>(R.id.buscar3)
        val buscar4 = findViewById<ImageButton>(R.id.buscar4)
        val buscar5 = findViewById<ImageButton>(R.id.buscar5)
        val buscar6 = findViewById<ImageButton>(R.id.buscar6)

        equipo = equipoService.findAll()
        caja = cajaService.descodificar(intent.getStringExtra("caja").toString())

        img(equipo[0].pokemon, buscar1)
        img(equipo[1].pokemon, buscar2)
        img(equipo[2].pokemon, buscar3)
        img(equipo[3].pokemon, buscar4)
        img(equipo[4].pokemon, buscar5)
        img(equipo[5].pokemon, buscar6)

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

    fun salir(view: View){

        finish()
        startActivity(Intent(this, MainActivity::class.java))

    }

    fun buscar(view: View){

        var buscar = false

        for (i in equipo){
            if (i.id > 0){
                buscar = true
            }
        }

        if (buscar){

            val fecha = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            val i = Intent(this, RecompensasActivity::class.java)

            i.putExtra("caja", cajaService.codificar(caja))
            busquedaService.update(fecha.toString(), true)

            equipoService.update(equipo)
            finish()
            startActivity(i)

        }

    }

    private fun ventana(boton: ImageButton, numero: Int){

        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.spinner_pokemons, null)
        val spinner = dialogLayout.findViewById<Spinner>(R.id.spinnerpokemons)

        spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cajaService.viewPokemon(caja))
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

                equipo[numero] = selecionado
                img(selecionado.pokemon, boton)
                Toast.makeText(this, "Seleccionaste a "+selecionado.apodo, Toast.LENGTH_LONG).show()

            }else{

                Toast.makeText(this, "Ese pokemon ya esta selecionado", Toast.LENGTH_LONG).show()

            }

            dialogInterface.dismiss()

        }
        builder.show()

    }

    private fun img(pokemon: Pokemon, boton: ImageButton){

        val bmp = BitmapFactory.decodeResource(resources, variablesImgPokemons.img(pokemon.img))

        boton.setImageBitmap(bmp)
        boton.setBackgroundResource(coloresTipos.colores(pokemon.tipoUno, pokemon.tipoDos))

    }

}