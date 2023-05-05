package com.isma.criapokemon.service

import com.isma.criapokemon.entity.Caja

interface CajaService {

    fun findAll(): ArrayList<Caja>
    fun add(caja: Caja)
    fun viewPokemon(): ArrayList<String>
    fun findByApodo(apodo: String): Caja
    fun update(caja: Caja)
    fun evolucion(caja: Caja, tipo: Int)
    fun evoTrue(caja: Caja): Boolean
    fun viewEvoluciones(caja: Caja): ArrayList<String>
    fun delete(id: Int)

}