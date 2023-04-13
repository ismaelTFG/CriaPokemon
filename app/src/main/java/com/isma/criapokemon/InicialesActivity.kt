package com.isma.criapokemon

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.isma.criapokemon.entity.Caja
import com.isma.criapokemon.service.impl.CajaServiceImpl
import com.isma.criapokemon.service.impl.PokemonServiceImpl

class InicialesActivity : AppCompatActivity() {

    private val pokemonService = PokemonServiceImpl(this)
    private val cajaService = CajaServiceImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciales)

    }

    fun bulbasaur(view: View){

        ventada(Caja(1,"bulbasaur", pokemonService.findById("1")))

    }

    fun charmarder(view: View){

        ventada(Caja(1, "Charmander", pokemonService.findById("4")))

    }

    fun squirtle(view: View){

        ventada(Caja(1, "Squirtle", pokemonService.findById("7")))

    }

    fun ventada(caja: Caja){

        val dialog = AlertDialog.Builder(this).setPositiveButton("si", DialogInterface.OnClickListener { dialogInterface, i ->

            cajaService.add(caja)
            startActivity(Intent(this, MainActivity::class.java))
            finish()

            Toast.makeText(this, "Tu inicial fue "+caja.pokemon.name, Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()

        }).setNegativeButton("no", DialogInterface.OnClickListener { dialogInterface, i ->

            Toast.makeText(this, "Tomate tu tiempo", Toast.LENGTH_SHORT).show()
            dialogInterface.dismiss()

        }).setTitle("Advertencia").setMessage("Si eliges este inicial no podras cambiarlo ¿estas seguro?").create()

        dialog.show()

    }

}