package com.isma.criapokemon.service.impl

import android.content.Context
import com.isma.criapokemon.R
import com.isma.criapokemon.entity.Caja
import com.isma.criapokemon.service.HuevoService
import java.io.BufferedReader
import java.io.InputStreamReader

class HuevoServiceImpl(context: Context): HuevoService {

    private val ventana = context

    override fun listAllName(): ArrayList<String> {

        val lista = ArrayList<String>()
        val isr = InputStreamReader(ventana.resources.openRawResource(R.raw.grupohuevo))
        val br = BufferedReader(isr)
        var line: String? = br.readLine()

        while (line != null){

            val text = line.split("*")

            lista.add(text[0])

            line = br.readLine()

        }

        br.close()
        isr.close()

        return lista

    }

    override fun listAllPokes(huevo: String): ArrayList<String> {

        val lista = ArrayList<String>()
        val isr = InputStreamReader(ventana.resources.openRawResource(R.raw.grupohuevo))
        val br = BufferedReader(isr)
        var line: String? = br.readLine()

        while (line != null){

            val text = line.split("*")

            if (text[0] == huevo){

                val pokes = text[1].split(",")

                for (i in pokes){

                    lista.add(i)

                }

            }

            line = br.readLine()

        }

        br.close()
        isr.close()

        return lista

    }

    override fun viewHuevos(caja: ArrayList<Caja>): ArrayList<String> {

        val exit = ArrayList<String>()
        val lista = listAllName()

        for (i in lista){

            val pokes = listAllPokes(i)

            for (j in caja){
                for (y in pokes){
                    if (j.pokemon.id == y){
                        if (!exit.contains(i)){

                            exit.add(i)

                        }
                    }
                }
            }

        }

        return exit

    }

}