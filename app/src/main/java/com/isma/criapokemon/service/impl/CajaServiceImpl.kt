package com.isma.criapokemon.service.impl

import android.app.Activity
import android.content.Context
import android.os.Environment
import com.isma.criapokemon.MainActivity
import com.isma.criapokemon.entity.Caja
import com.isma.criapokemon.entity.Pokemon
import com.isma.criapokemon.repository.Sqlite
import com.isma.criapokemon.service.CajaService
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter

class CajaServiceImpl(context: Context): CajaService {

    private val db = Sqlite(context)
    private val pokemonService = PokemonServiceImpl(context)
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

    override fun evolucion(caja: Caja, tipo: Int, context: Context) {

        val evolucion = caja.pokemon.evolucion.split(",")

        when(caja.pokemon.name){
            "lickitung" -> {
                evoConApodo("desenrollar", evolucion, caja, tipo)
            }
            "tangela" -> {
                evoConApodo("poderpasado", evolucion, caja, tipo)
            }
            "tyrogue", "ague", "machogue", "drowgue", "tyrochum", "tyrokid", "tyroby", "tyroge jr." -> {
                val i = evolucion[tipo].split("=")

                if (caja.getNivel() >= i[0].toInt()){
                    if (caja.apodo.replace("\\s".toRegex(), "") == "lee"){

                        caja.pokemon = pokemonService.findById(i[1])
                        update(caja)
                        pokedexService.visible(i[1])

                    }
                    if (caja.apodo.replace("\\s".toRegex(), "") == "chan"){

                        caja.pokemon = pokemonService.findById(i[2])
                        update(caja)
                        pokedexService.visible(i[2])

                    }
                    if (caja.apodo.replace("\\s".toRegex(), "") == "top"){

                        caja.pokemon = pokemonService.findById(i[3])
                        update(caja)
                        pokedexService.visible(i[3])

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

                                val evo = pokemonService.findById(i[1])

                                if (caja.apodo == caja.pokemon.name){

                                    caja.apodo = evo.name

                                }

                                caja.pokemon = evo
                                update(caja)
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

    override fun findByid(id: Int): Caja {

        val lista = findAll()

        lista.forEach {
            if (it.id == id){

                return it

            }
        }

        return Caja(0, "", Pokemon("", "", "", "", "", ""))

    }

    override fun findByidPokemon(id: String): Caja {

        val lista = findAll()

        lista.forEach {
            if (it.pokemon.id == id){

                return it

            }
        }

        return Caja(0, "", Pokemon("", "", "", "", "", ""))

    }

    private fun evoConApodo(apodo: String, evolucion: List<String>, caja: Caja, tipo: Int){

        val i = evolucion[tipo].split("=")

        if (caja.apodo.replace("\\s".toRegex(), "") == apodo){
            if (caja.getNivel() >= i[0].toInt()){

                caja.pokemon = pokemonService.findById(i[1])
                update(caja)
                pokedexService.visible(i[1])

            }
        }

    }

    private fun evoConObjeto(objeto: Int, caja: Caja, evolucion: String){

        val mochila = db.findByObjetoMochila(objeto, db.writableDatabase)

        if (mochila.cantidad > 0){

            val pokemon = pokemonService.findById(evolucion)

            if (caja.apodo == caja.pokemon.name){

                caja.apodo = pokemon.name

            }

            caja.pokemon = pokemon
            pokedexService.visible(evolucion)
            update(caja)
            db.updateMochila(mochila, -1, db.writableDatabase)

        }

    }

}