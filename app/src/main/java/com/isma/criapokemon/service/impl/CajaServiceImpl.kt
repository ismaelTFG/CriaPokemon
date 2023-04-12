package com.isma.criapokemon.service.impl

import android.content.Context
import com.isma.criapokemon.entity.Caja
import com.isma.criapokemon.repository.Sqlite
import com.isma.criapokemon.service.CajaService

class CajaServiceImpl(context: Context): CajaService {

    private val db = Sqlite(context)

    override fun findAll(): ArrayList<Caja> {

        return db.findAllCaja(db.writableDatabase)

    }

    override fun add(caja: Caja) {

        db.addCaja(caja, db.writableDatabase)

    }

    override fun viewPokemon(): ArrayList<String> {

        val exit = ArrayList<String>()

        for (i in findAll()){

            exit.add(i.apodo)

        }

        return exit

    }

    override fun findByApodo(apodo: String): Caja {

        return db.findByApodoCaja(apodo, db.writableDatabase)

    }

    override fun update(caja: Caja) {

        return db.updateCaja(caja, db.writableDatabase)

    }

}