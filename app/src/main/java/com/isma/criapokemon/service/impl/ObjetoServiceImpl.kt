package com.isma.criapokemon.service.impl

import android.content.Context
import com.isma.criapokemon.R
import com.isma.criapokemon.entity.Objeto
import com.isma.criapokemon.service.ObjetoService
import java.io.BufferedReader
import java.io.InputStreamReader

class ObjetoServiceImpl(context: Context): ObjetoService {

    private val ventana = context

    override fun listAll(): ArrayList<Objeto> {

        val lista = ArrayList<Objeto>()

        val isr = InputStreamReader(ventana.resources.openRawResource(R.raw.objeto))
        val br = BufferedReader(isr)
        var line: String? = br.readLine()

        while (line != null){

            val text = line.split("*")

            lista.add(Objeto(text[0].toInt(), text[1], text[2].toInt()))

            line = br.readLine()

        }

        isr.close()
        br.close()

        return lista

    }

    override fun findById(id: Int): Objeto {

        val lista = listAll()

        lista.forEach {
            if (it.id == id){

                return it

            }
        }

        return Objeto(0, "", 0)

    }

}