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
            "_3" -> {
                return R.drawable._3
            }
            "_4" -> {
                return R.drawable._4
            }
            "_5" -> {
                return R.drawable._5
            }
            "_6" -> {
                return R.drawable._6
            }
            "_7" -> {
                return R.drawable._7
            }
            else -> {
                return 0
            }
        }

    }

}