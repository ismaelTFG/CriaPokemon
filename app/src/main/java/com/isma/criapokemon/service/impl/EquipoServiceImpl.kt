package com.isma.criapokemon.service.impl

import android.content.Context
import com.isma.criapokemon.entity.Caja
import com.isma.criapokemon.repository.Sqlite
import com.isma.criapokemon.service.EquipoService

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

}