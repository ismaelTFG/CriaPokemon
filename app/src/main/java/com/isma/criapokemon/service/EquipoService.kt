package com.isma.criapokemon.service

import com.isma.criapokemon.entity.Caja

interface EquipoService {

    fun findAll(): ArrayList<Caja>
    fun update(equipo: ArrayList<Caja>)

}