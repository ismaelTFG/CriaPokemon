package com.isma.criapokemon.service

import com.isma.criapokemon.entity.Objeto

interface ObjetoService {

    fun add()
    fun listAll(): ArrayList<Objeto>

}