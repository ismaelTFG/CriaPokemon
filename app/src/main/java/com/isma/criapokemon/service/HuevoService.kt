package com.isma.criapokemon.service

import com.isma.criapokemon.entity.Caja

interface HuevoService {

    fun listAllName(): ArrayList<String>
    fun listAllPokes(huevo: String): ArrayList<String>
    fun viewHuevos(caja: ArrayList<Caja>): ArrayList<String>

}