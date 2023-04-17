package com.isma.criapokemon.service

import com.isma.criapokemon.entity.Mochila
import com.isma.criapokemon.entity.Objeto

interface MochilaService {

    fun add(objeto: Objeto)
    fun listAll(): ArrayList<Mochila>

}