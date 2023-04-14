package com.isma.criapokemon.service.impl

import android.content.Context
import com.isma.criapokemon.R
import com.isma.criapokemon.entity.Recompensas
import com.isma.criapokemon.repository.Sqlite
import com.isma.criapokemon.service.RecompensasService
import java.io.BufferedReader
import java.io.InputStreamReader

class RecompensasServiceImpl(context: Context): RecompensasService {

    private val ventana = context
    private val db = Sqlite(ventana)

    override fun listAll(): ArrayList<Recompensas> {

        return db.findAllRecompensas(db.writableDatabase)

    }

    override fun add() {

        val isr = InputStreamReader(ventana.resources.openRawResource(R.raw.recompensas))
        val br = BufferedReader(isr)
        var line: String? = br.readLine()

        while (line != null){

            val text = line.split("*")

            db.addRecompensas(text[0].toInt(), text[1].toInt(), db.writableDatabase)

            line = br.readLine()

        }

        br.close()
        isr.close()

    }



}