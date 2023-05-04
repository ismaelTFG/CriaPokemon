package com.isma.criapokemon

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.TextViewCompat
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import com.isma.criapokemon.service.impl.CajaServiceImpl
import com.isma.criapokemon.variablesdrawable.VariablesImgPokemons

class PokemonActivity : AppCompatActivity() {

    private val cajaService = CajaServiceImpl(this)
    private val variablesImgPokemons = VariablesImgPokemons()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon)

        val intent = Intent(this, CajaActivity::class.java)
        val layout = findViewById<LinearLayout>(R.id.pokemon)
        mostrar(layout, intent)

    }

    fun salir(view: View){

        finish()

    }

    private fun mostrar(layout: LinearLayout, intent: Intent){

        val lista = cajaService.findAll()

        for (i in lista.indices){

            val img = layoutInflater.inflate(R.layout.caja_pokemon, null).apply {
                layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            }
            val nombre = img.findViewById<TextView>(R.id.nombrepokecaja)
            val poke = img.findViewById<ImageButton>(R.id.imgpokecaja)

            nombre.setText(lista[i].apodo)
            poke.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(lista[i].pokemon.img)))
            poke.setOnClickListener {

                intent.putExtra("numero", i)

                startActivity(intent)

            }

            layout.addView(img)

        }

    }

}