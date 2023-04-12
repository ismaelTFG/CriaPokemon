package com.isma.criapokemon.variablesdrawable

import com.isma.criapokemon.R

class VariablesImgPokemons {

    fun img(numero: String): Int {

        when(numero){
            "_1" -> {
                return R.drawable._1
            }
            "_2" -> {
                return R.drawable._2
            }
            else -> {
                return 0
            }
        }

    }

}