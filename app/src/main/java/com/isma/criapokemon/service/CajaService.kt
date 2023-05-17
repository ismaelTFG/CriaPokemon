package com.isma.criapokemon.service

import android.content.Context
import com.isma.criapokemon.entity.Caja
import java.io.OutputStream

interface CajaService {

    fun findAll(): ArrayList<Caja>
    fun add(caja: Caja)
    fun viewPokemon(): ArrayList<String>
    fun findByApodo(apodo: String): Caja
    fun update(caja: Caja)
    fun evolucion(caja: Caja, tipo: Int, context: Context)
    fun evoTrue(caja: Caja): Boolean
    fun viewEvoluciones(caja: Caja): ArrayList<String>
    fun delete(id: Int)
    fun findByid(id: Int): Caja
    fun findByidPokemon(id: String): Caja

}