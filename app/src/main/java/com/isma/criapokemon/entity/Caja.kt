package com.isma.criapokemon.entity

class Caja(
    var id: Int,
    var apodo: String,
    var pokemon: Pokemon) {


    private var nivel = 1
    private var macho = genero()

    fun setNivel(nivel: Int){

        this.nivel = nivel

    }
    fun setMacho(genero: Int){

        if (genero == 0){

            macho = false

        }

    }

    fun getNivel(): Int{

        return nivel

    }
    fun getGenero(): Int{

        if (macho){

            return 1

        }else{

            return 0

        }

    }

    private fun genero(): Boolean{

        val genero = (Math.random() * 2).toInt()

        if (genero == 0){
            return false
        }

        return true

    }

    override fun toString(): String {

        val exit = StringBuilder()

        exit.append("especie: ").append(pokemon.name).append("\n")
        exit.append("tipos: ").append(pokemon.tipoUno).append(" ").append(pokemon.tipoDos).append("\n")
        exit.append("genero: ")
        if (macho){
            exit.append("macho").append("\n")
        }else{
            exit.append("Hembra").append("\n")
        }
        exit.append("nivel: ").append(nivel).append("\n")

        return exit.toString()

    }

}