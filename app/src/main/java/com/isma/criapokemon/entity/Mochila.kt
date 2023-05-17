package com.isma.criapokemon.entity

class Mochila(
    val id: Int,
    val objeto: Objeto,
    var cantidad: Int) {

    override fun toString(): String {

        val exit = StringBuilder()

        exit.append(id).append("*").append(objeto.id).append("*").append(cantidad).append("\n")

        return exit.toString()

    }

}