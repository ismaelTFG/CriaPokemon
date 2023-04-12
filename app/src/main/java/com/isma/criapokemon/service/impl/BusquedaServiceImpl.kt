package com.isma.criapokemon.service.impl

import android.content.Context
import com.isma.criapokemon.repository.Sqlite
import com.isma.criapokemon.service.BusquedaService

class BusquedaServiceImpl(context: Context): BusquedaService {

    private val db = Sqlite(context)

    override fun hora(): String {

        return db.findHora(db.writableDatabase)

    }

    override fun buscando(): Boolean {

        if (db.findbuscar(db.writableDatabase) == 0){

            return false

        }

        return true

    }

    override fun update(hora: String, buscando: Boolean) {

        var buscar = 0

        if(buscando){

            buscar = 1

        }

        db.updateBusqueda(hora, buscar, db.writableDatabase)

    }

}