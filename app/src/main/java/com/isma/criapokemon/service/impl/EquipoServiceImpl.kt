package com.isma.criapokemon.service.impl

import android.app.Activity
import android.content.Context
import com.isma.criapokemon.MainActivity
import com.isma.criapokemon.entity.Caja
import com.isma.criapokemon.repository.Sqlite
import com.isma.criapokemon.service.EquipoService
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class EquipoServiceImpl(context: Context): EquipoService {

    private val db = Sqlite(context)

    override fun findAll(): ArrayList<Caja> {

        return db.findAllEquipo(db.writableDatabase)

    }

    override fun update(equipo: ArrayList<Caja>) {

        for (i in 1..6){

            db.updateEquipo(i, equipo[i-1].id, db.writableDatabase)

        }

    }

    override fun subidaNivel() {

        val lista = findAll()

        for (i in lista){
            if (i.getNivel() < 100){

                i.setNivel(i.getNivel()+1)
                db.updateCaja(i, db.writableDatabase)

            }
        }

    }

}