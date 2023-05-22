package com.isma.criapokemon

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import com.isma.criapokemon.service.impl.CajaServiceImpl
import com.isma.criapokemon.variablesdrawable.ColoresTipos
import com.isma.criapokemon.variablesdrawable.VariablesImgPokemons

class PokemonActivity : AppCompatActivity() {

    private val cajaService = CajaServiceImpl(this)
    private val variablesImgPokemons = VariablesImgPokemons()
    private val coloresTipos = ColoresTipos()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        val i = Intent(this, CajaActivity::class.java)
        val layout = findViewById<LinearLayout>(R.id.pokemon)
        val datos = intent.getStringExtra("caja").toString()
        mostrar(layout, i, datos)

    }

    fun salir(view: View){

        finish()
        startActivity(Intent(this, MainActivity::class.java))

    }

    private fun mostrar(layout: LinearLayout, intent: Intent, datos: String){

        val lista = cajaService.descodificar(datos)

        for (i in lista.indices){

            val img = layoutInflater.inflate(R.layout.caja_pokemon, null).apply {
                layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            }
            val nombre = img.findViewById<TextView>(R.id.nombrepokecaja)
            val poke = img.findViewById<ImageButton>(R.id.imgpokecaja)

            nombre.setText("\n\n"+lista[i].apodo)
            poke.setBackgroundResource(coloresTipos.colores(lista[i].pokemon.tipoUno, lista[i].pokemon.tipoDos))
            poke.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(lista[i].pokemon.img)))
            poke.setOnClickListener {

                intent.putExtra("numero", i)
                intent.putExtra("caja", datos)

                finish()
                startActivity(intent)

            }

            layout.addView(img)

        }

    }

}