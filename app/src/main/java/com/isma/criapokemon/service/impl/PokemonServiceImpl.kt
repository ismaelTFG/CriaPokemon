package com.isma.criapokemon.service.impl

import android.content.Context
import com.isma.criapokemon.R
import com.isma.criapokemon.entity.Pokemon
import com.isma.criapokemon.service.PokemonService
import java.io.BufferedReader
import java.io.InputStreamReader

class PokemonServiceImpl(context: Context): PokemonService {

    private val ventana = context

    override fun listAll(): ArrayList<Pokemon> {

        val lista = ArrayList<Pokemon>()
        val isr = InputStreamReader(ventana.resources.openRawResource(R.raw.pokemon))
        val br = BufferedReader(isr)
        var line: String? = br.readLine()

        while (line != null){

            val text = line.split("*")

            lista.add(Pokemon(text[0], text[1], text[2], text[3], text[4], text[5]))

            line = br.readLine()

        }

        br.close()
        isr.close()

        return lista

    }

    override fun findById(id: String): Pokemon {

        val lista = listAll()

        lista.forEach {
            if (it.id == id){

                return it

            }
        }

        return Pokemon("", "", "", "", "", "")

    }

    override fun findByNoFusion(): ArrayList<String> {

        val exit = ArrayList<String>()
        val lista = listAll()

        for (i in lista){

            val char = i.id.toCharArray()
            var fusion = false

            for (j in char){
                if (j == '_'){

                    fusion = true

                }
            }

            if (!fusion){

                exit.add(i.id)

            }

        }

        return exit

    }

}