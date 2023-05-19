package com.isma.criapokemon.service.impl

import android.content.Context
import com.isma.criapokemon.repository.Sqlite
import com.isma.criapokemon.service.PokedexService

class PokedexServiceImpl(context: Context): PokedexService {

    private val ventana = context
    private val db = Sqlite(ventana)
    private val pokemonService = PokemonServiceImpl(ventana)

    override fun add() {

        val lista = pokemonService.listAll()

        lista.forEach{

            db.addPokedex(it.id, db.writableDatabase)

        }

    }

    override fun findAll(): ArrayList<Boolean> {

        return db.findAllPokedex(db.writableDatabase)

    }

    override fun visible(id: String) {

        return db.visiblePokedex(id, db.writableDatabase)

    }

    override fun findById(id: String): Boolean {

        return db.findByIdPokedex(id, db.writableDatabase)

    }

    override fun especies(): Int {

        val lista = findAll()
        var contador = 0

        lista.forEach {
            if (it){
                contador++
            }
        }

        return contador

    }

}