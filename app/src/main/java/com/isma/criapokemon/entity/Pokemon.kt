package com.isma.criapokemon.entity

import android.content.Context
import com.isma.criapokemon.R
import java.io.BufferedReader
import java.io.InputStreamReader

class Pokemon(
    val id: String,
    val name: String,
    val img: String,
    val tipoUno: String,
    val tipoDos: String) {

    fun toString(context: Context, id: String): String {

        val exit = StringBuilder()

        exit.append("especie: ").append(name).append("\n")
        exit.append("tipos: ").append(tipoUno).append(" ").append(tipoDos).append("\n")
        exit.append("descripcion: ").append(descripcion(context, id))

        return exit.toString()

    }

    fun descripcion(context: Context, id: String): String{

        val isr = InputStreamReader(context.resources.openRawResource(R.raw.pokedex))
        val br = BufferedReader(isr)
        var line: String? = br.readLine()
        val numero = ArrayList<String>()
        val descripcion = ArrayList<String>()
        var contador = 0

        while (line != null){

            val text = line.split("*")

            numero.add(text[0])
            descripcion.add(text[1])

            line = br.readLine()

        }

        for (i in numero){

            contador++

            if (i == id){

                return descripcion[contador]

            }
        }

        br.close()
        isr.close()

        return ""

    }

}