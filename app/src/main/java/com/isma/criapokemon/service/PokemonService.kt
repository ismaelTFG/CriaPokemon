package com.isma.criapokemon.service

import com.isma.criapokemon.entity.Pokemon

interface PokemonService {

    fun listAll(): ArrayList<Pokemon>
    fun add()
    fun findById(id: String): Pokemon

}