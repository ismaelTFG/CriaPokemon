package com.isma.criapokemon.entity

class Caja(
    var id: Int,
    var apodo: String,
    var pokemon: Pokemon) {


    private var nivel = 1

    fun setNivel(nivel: Int){

        this.nivel = nivel

    }

    fun getNivel(): Int{

        return nivel

    }

    override fun toString(): String {

        val exit = StringBuilder()

        exit.append("especie: ").append(pokemon.name).append("\n")
        exit.append("tipos: ")
        if (pokemon.tipoUno == pokemon.tipoDos){
            exit.append(pokemon.tipoUno).append("\n")
        }else{
            exit.append(pokemon.tipoUno).append(" ").append(pokemon.tipoDos).append("\n")
        }
        exit.append("nivel: ").append(nivel).append("\n")

        return exit.toString()

    }

}