package com.isma.criapokemon.service.impl

import android.content.Context
import com.isma.criapokemon.R
import com.isma.criapokemon.entity.Recompensas
import com.isma.criapokemon.service.RecompensasService
import java.io.BufferedReader
import java.io.InputStreamReader

class RecompensasServiceImpl(context: Context): RecompensasService {

    private val ventana = context
    private val pokemonService = PokemonServiceImpl(ventana)

    override fun listAll(): ArrayList<Recompensas> {

        val isr = InputStreamReader(ventana.resources.openRawResource(R.raw.recompensas))
        val br = BufferedReader(isr)
        val lista = ArrayList<Recompensas>()
        var line: String? = br.readLine()

        while (line != null){

            val text = line.split("*")

            lista.add(Recompensas(pokemonService.findById(text[0]), text[1].toInt()))

            line = br.readLine()

        }

        br.close()
        isr.close()

        return lista

    }

}