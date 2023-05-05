package com.isma.criapokemon.service.impl

import android.content.Context
import com.isma.criapokemon.entity.Mochila
import com.isma.criapokemon.entity.Objeto
import com.isma.criapokemon.repository.Sqlite
import com.isma.criapokemon.service.MochilaService

class MochilaServiceImpl(context: Context): MochilaService {

    private val db = Sqlite(context)

    override fun add(objeto: Objeto) {

        val mochila = db.findByObjetoMochila(objeto.id, db.writableDatabase)

        if (mochila.id == -1){

            db.addMochila(Mochila(listAll().size, objeto, 1), db.writableDatabase)

        }else{

            db.updateMochila(mochila,1,  db.writableDatabase)

        }

    }

    override fun listAll(): ArrayList<Mochila> {

        return db.findAllMochila(db.writableDatabase)

    }

}