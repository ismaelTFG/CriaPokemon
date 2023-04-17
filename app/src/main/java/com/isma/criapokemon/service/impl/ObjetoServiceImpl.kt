package com.isma.criapokemon.service.impl

import android.content.Context
import com.isma.criapokemon.R
import com.isma.criapokemon.entity.Objeto
import com.isma.criapokemon.repository.Sqlite
import com.isma.criapokemon.service.ObjetoService
import java.io.BufferedReader
import java.io.InputStreamReader

class ObjetoServiceImpl(context: Context): ObjetoService {

    private val ventana = context
    private val db = Sqlite(ventana)

    override fun add() {

        val isr = InputStreamReader(ventana.resources.openRawResource(R.raw.objeto))
        val br = BufferedReader(isr)
        var line: String? = br.readLine()

        while (line != null){

            val text = line.split("*")

            db.addObjetos(Objeto(text[0].toInt(), text[1], text[2].toInt()), db.writableDatabase)

            line = br.readLine()

        }

        br.close()
        isr.close()

    }

    override fun listAll(): ArrayList<Objeto> {

        return db.findAllObjetos(db.writableDatabase)

    }

}