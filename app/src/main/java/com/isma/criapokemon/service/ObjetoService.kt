package com.isma.criapokemon.service

import com.isma.criapokemon.entity.Objeto

interface ObjetoService {

    fun listAll(): ArrayList<Objeto>
    fun findById(id: Int): Objeto

}