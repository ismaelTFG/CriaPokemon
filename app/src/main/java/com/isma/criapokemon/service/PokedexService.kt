package com.isma.criapokemon.service

interface PokedexService {

    fun add(id: String)
    fun findAll(): ArrayList<Boolean>
    fun visible(id: String)
    fun findById(id: String): Boolean

}