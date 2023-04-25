package com.isma.criapokemon.service.impl

import android.content.Context
import com.isma.criapokemon.R
import com.isma.criapokemon.entity.Pokemon
import com.isma.criapokemon.repository.Sqlite
import com.isma.criapokemon.service.PokemonService
import java.io.BufferedReader
import java.io.InputStreamReader

class PokemonServiceImpl(context: Context): PokemonService {

    private val ventana = context
    private val db = Sqlite(ventana)

    override fun listAll(): ArrayList<Pokemon> {

        return db.findAllPokemon(db.writableDatabase)

    }

    override fun add() {

        val isr = InputStreamReader(ventana.resources.openRawResource(R.raw.pokemon))
        val br = BufferedReader(isr)
        var line: String? = br.readLine()

        while (line != null){

            val text = line.split("*")

            db.addPokemon(Pokemon(text[0], text[1], text[2], text[3], text[4], text[5]), db.writableDatabase)

            line = br.readLine()

        }

        br.close()
        isr.close()

    }

    override fun findById(id: String): Pokemon {

         return db.findByIdPokemon(id, db.writableDatabase)

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