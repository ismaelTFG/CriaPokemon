package com.isma.criapokemon.service

import android.content.Context

interface PokedexService {

    fun add()
    fun findAll(): ArrayList<Boolean>
    fun visible(id: String)
    fun findById(id: String): Boolean
    fun especies(): Int

}