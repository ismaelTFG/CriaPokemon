package com.isma.criapokemon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun caja(view: View){

        startActivity(Intent(this, CajaActivity::class.java))

    }

    fun criar(view: View){

        startActivity(Intent(this, CriarActivity::class.java))

    }

    fun buscar(view: View){

        startActivity(Intent(this, BuscarActivity::class.java))

    }

    fun pokedex(view: View){

        startActivity(Intent(this, PokedexActivity::class.java))

    }

    fun objeto(view: View){

        startActivity(Intent(this, ObjetosActivity::class.java))

    }

}