package com.isma.criapokemon.service.impl

import android.content.Context
import com.isma.criapokemon.entity.Caja
import com.isma.criapokemon.repository.Sqlite
import com.isma.criapokemon.service.CajaService

class CajaServiceImpl(context: Context): CajaService {

    private val db = Sqlite(context)
    private val pokedexService = PokedexServiceImpl(context)

    override fun findAll(): ArrayList<Caja> {

        return db.findAllCaja(db.writableDatabase)

    }

    override fun add(caja: Caja) {

        db.addCaja(caja, db.writableDatabase)

    }

    override fun viewPokemon(): ArrayList<String> {

        val exit = ArrayList<String>()

        for (i in findAll()){

            exit.add(i.apodo)

        }

        return exit

    }

    override fun findByApodo(apodo: String): Caja {

        return db.findByApodoCaja(apodo, db.writableDatabase)

    }

    override fun update(caja: Caja) {

        return db.updateCaja(caja, db.writableDatabase)

    }

    override fun evolucion(caja: Caja, tipo: Int) {

        val evolucion = caja.pokemon.evolucion.split(",")

        when(caja.pokemon.name){
            "lickitung" -> {
                evoConApodo("desenrollar", evolucion, caja, tipo)
            }
            "tangela" -> {
                evoConApodo("poder pasado", evolucion, caja, tipo)
            }
            "tyrogue" -> {
                val i = evolucion[tipo].split("=")

                if (caja.getNivel() >= i[0].toInt()){
                    if (caja.apodo == "lee"){

                        caja.pokemon = db.findByIdPokemon(i[1], db.writableDatabase)
                        db.updateCaja(caja, db.writableDatabase)
                        pokedexService.visible(i[1])

                    }
                    if (caja.apodo == "chan"){

                        caja.pokemon = db.findByIdPokemon(i[2], db.writableDatabase)
                        db.updateCaja(caja, db.writableDatabase)
                        pokedexService.visible(i[1])

                    }
                    if (caja.apodo == "top"){

                        caja.pokemon = db.findByIdPokemon(i[3], db.writableDatabase)
                        db.updateCaja(caja, db.writableDatabase)
                        pokedexService.visible(i[1])

                    }
                }

            }
            "mime jr." -> {
                evoConApodo("mimetico", evolucion, caja, tipo)
            }
            else ->{
                val i = evolucion[tipo].split("=")
                when(i[0]){
                    "piedra trueno" -> {
                        evoConObjeto(1, caja, i[1])
                    }
                    "piedra lunar" -> {
                        evoConObjeto(2, caja, i[1])
                    }
                    "piedra fuego" -> {
                        evoConObjeto(3, caja, i[1])
                    }
                    "piedra hoja" -> {
                        evoConObjeto(4, caja, i[1])
                    }
                    "piedra solar" -> {
                        evoConObjeto(5, caja, i[1])
                    }
                    "piedra agua" -> {
                        evoConObjeto(6, caja, i[1])
                    }
                    "roca del rey" -> {
                        evoConObjeto(7, caja, i[1])
                    }
                    "revestimiento metalico" -> {
                        evoConObjeto(8, caja, i[1])
                    }
                    "protector" -> {
                        evoConObjeto(9, caja, i[1])
                    }
                    "escama dragon" -> {
                        evoConObjeto(10, caja, i[1])
                    }
                    "electrizador" -> {
                        evoConObjeto(11, caja, i[1])
                    }
                    "magmatizador" -> {
                        evoConObjeto(12, caja, i[1])
                    }
                    "piedra dia" -> {
                        evoConObjeto(13, caja, i[1])
                    }
                    "piedra noche" -> {
                        evoConObjeto(14, caja, i[1])
                    }
                    "piedra hielo" -> {
                        evoConObjeto(15, caja, i[1])
                    }
                    "mejora" -> {
                        evoConObjeto(16, caja, i[1])
                    }
                    "disco extraño" -> {
                        evoConObjeto(17, caja, i[1])
                    }
                    "piedra oval" -> {
                        evoConObjeto(18, caja, i[1])
                    }
                    else -> {
                        if (i[0] != "no"){
                            if (i[0].toInt() <= caja.getNivel()){

                                val evo = db.findByIdPokemon(i[1], db.writableDatabase)

                                if (caja.apodo == caja.pokemon.name){

                                    caja.apodo = evo.name

                                }

                                caja.pokemon = evo
                                db.updateCaja(caja, db.writableDatabase)
                                pokedexService.visible(i[1])

                            }
                        }
                    }
                }
            }
        }

    }

    override fun evoTrue(caja: Caja): Boolean {

        val lista = db.findAllMochila(db.writableDatabase)
        val evolucion = caja.pokemon.evolucion.split(",")

        for (i in evolucion.indices){

            val evo = evolucion[i].split("=")

            for (j in lista){
                if (evo[0] == j.objeto.nombre){
                    if (j.cantidad > 0){

                        return true

                    }
                }
            }

            if (evo[0] != "no"){
                if (evo[0].length <= 2){
                    if (evo[0].toInt() <= caja.getNivel()){

                        return true

                    }
                }
            }

        }

        return false

    }

    override fun viewEvoluciones(caja: Caja): ArrayList<String> {

        val lista = ArrayList<String>()
        val evolucion = caja.pokemon.evolucion.split(",")

        for (i in evolucion.indices){

            val evo = evolucion[i].split("=")

            lista.add(evo[0])

        }

        return lista

    }

    override fun delete(id: Int) {

        db.deleteCaja(id, db.writableDatabase)

    }

    private fun evoConApodo(apodo: String, evolucion: List<String>, caja: Caja, tipo: Int){

        val i = evolucion[tipo].split("=")

        if (caja.apodo == apodo){
            if (caja.getNivel() >= i[0].toInt()){

                caja.pokemon = db.findByIdPokemon(i[1], db.writableDatabase)
                db.updateCaja(caja, db.writableDatabase)
                pokedexService.visible(i[1])

            }
        }

    }

    private fun evoConObjeto(objeto: Int, caja: Caja, evolucion: String){

        val mochila = db.findByObjetoMochila(objeto, db.writableDatabase)

        if (mochila.cantidad > 0){

            val pokemon = db.findByIdPokemon(evolucion, db.writableDatabase)

            if (caja.apodo == caja.pokemon.name){

                caja.apodo = pokemon.name

            }

            caja.pokemon = pokemon
            pokedexService.visible(evolucion)
            db.updateCaja(caja, db.writableDatabase)
            db.updateMochila(mochila, -1, db.writableDatabase)

        }

    }

}