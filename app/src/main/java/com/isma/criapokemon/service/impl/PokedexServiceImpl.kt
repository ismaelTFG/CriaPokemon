package com.isma.criapokemon.service.impl

import android.content.Context
import com.isma.criapokemon.repository.Sqlite
import com.isma.criapokemon.service.PokedexService

class PokedexServiceImpl(context: Context): PokedexService {

    private val db = Sqlite(context)

    override fun add(id: String) {

        return db.addPokedex(id, db.writableDatabase)

    }

    override fun findAll(): ArrayList<Boolean> {

        return db.findAllPokedex(db.writableDatabase)

    }

    override fun visible(id: String) {

        return db.visiblePokedex(id, db.writableDatabase)

    }

}