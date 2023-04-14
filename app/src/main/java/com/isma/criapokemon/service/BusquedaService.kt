package com.isma.criapokemon.service

interface BusquedaService {

    fun hora(): String
    fun buscando(): Boolean
    fun update(hora: String, buscando: Boolean)

}