package com.isma.criapokemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.isma.criapokemon.service.impl.MochilaServiceImpl

class ObjetosActivity : AppCompatActivity() {

    private val mochilaService = MochilaServiceImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_objetos)

        val objetos = findViewById<TextView>(R.id.nombreobjeto)

        objetos.setText(mostrar())

    }

    fun salir(view: View){

        finish()

    }

    private fun mostrar(): String{

        val mochila = mochilaService.listAll()
        var exit = ""

        for (i in mochila){

            exit = exit + "nombre: ${i.objeto.nombre}, cantidad: ${i.cantidad}\n"

        }

        return exit

    }

}