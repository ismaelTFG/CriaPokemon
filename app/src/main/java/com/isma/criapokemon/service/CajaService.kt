package com.isma.criapokemon.service

import com.isma.criapokemon.entity.Caja

interface CajaService {

    fun findAll(): ArrayList<Caja>
    fun add(caja: Caja)
    fun viewPokemon(): ArrayList<String>

}