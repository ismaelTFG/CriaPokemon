package com.isma.criapokemon.repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.isma.criapokemon.entity.*
import com.isma.criapokemon.service.impl.ObjetoServiceImpl
import com.isma.criapokemon.service.impl.PokemonServiceImpl

class Sqlite(context: Context): SQLiteOpenHelper(context, "criapokemon", null, 1) {

    private val pokemonService = PokemonServiceImpl(context)
    private val objetoService = ObjetoServiceImpl(context)

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE caja (id INTEGER PRIMARY KEY, apodo TEXT, id_pokemon TEXT, nivel INTEGER, FOREIGN KEY (id_pokemon) REFERENCES pokemon(id))")
        db.execSQL("CREATE TABLE equipo (id INTEGER PRIMARY KEY, id_caja INTEGER)")
        db.execSQL("CREATE TABLE busqueda (id INTEGER PRIMARY KEY, hora TEXT, buscando INTEGER)")
        db.execSQL("CREATE TABLE pokedex (id TEXT PRIMARY KEY, visible INTEGER)")
        db.execSQL("CREATE TABLE mochila (id INTEGER PRIMARY KEY, id_objeto INTEGER, cantidad INTEGER)")

        addBusqueda(db)
        for (i in 1..6){

            addEquipo(i, -10+i, db)

        }

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun findAllCaja (db: SQLiteDatabase): ArrayList<Caja>{

        val resultado = db.rawQuery("SELECT * FROM caja", null)
        val lista = ArrayList<Caja>()

        if (resultado!!.moveToFirst()){

            while (!resultado.isAfterLast){

                val caja = Caja(resultado.getInt(0), resultado.getString(1), pokemonService.findById(resultado.getString(2)))

                caja.setNivel(resultado.getInt(3))

                lista.add(caja)
                resultado.moveToNext()

            }

        }

        return lista

    }

    fun findByApodoCaja (apodo: String, db: SQLiteDatabase): Caja{

        val lista = findAllCaja(db)

        for (i in lista){
            if (i.apodo == apodo){

                return i

            }
        }

        return Caja(0, "", Pokemon("", "", "", "", "", ""))

    }

    fun addCaja (caja: Caja, db: SQLiteDatabase){

        val add = ContentValues()

        add.put("id", caja.id)
        add.put("apodo", caja.apodo)
        add.put("id_pokemon", caja.pokemon.id)
        add.put("nivel", caja.getNivel())

        db.insert("caja", null, add)

    }

    fun updateCaja (caja: Caja, db: SQLiteDatabase){

        val add = ContentValues()

        add.put("apodo", caja.apodo)
        add.put("id_pokemon", caja.pokemon.id)
        add.put("nivel", caja.getNivel())

        db.update("caja", add, "id=${caja.id}", null)

    }

    fun deleteCaja (id: Int, db: SQLiteDatabase){

        db.delete("caja", "id=$id", null)

    }

    fun findByIdCaja (id: Int, db: SQLiteDatabase): Caja{

        val resultado = db.rawQuery("SELECT * FROM caja WHERE id=$id", null)

        if (resultado!!.moveToFirst()){

            while (!resultado.isAfterLast){

                val caja = Caja(resultado.getInt(0), resultado.getString(1), pokemonService.findById(resultado.getString(2)))

                caja.setNivel(resultado.getInt(3))

                return caja

            }

        }

        return Caja(0, "", Pokemon("", "", "", "", "", ""))

    }

    fun findByIdPokemonCaja (id: String, db: SQLiteDatabase): ArrayList<Caja>{

        val resultado = db.rawQuery("SELECT * FROM caja WHERE id_pokemon='$id'", null)
        val lista = ArrayList<Caja>()

        if (resultado!!.moveToFirst()){

            while (!resultado.isAfterLast){

                val caja = Caja(resultado.getInt(0), resultado.getString(1), pokemonService.findById(resultado.getString(2)))

                caja.setNivel(resultado.getInt(3))

                lista.add(caja)
                resultado.moveToNext()

            }

        }

        return lista

    }

    fun findAllEquipo(db: SQLiteDatabase): ArrayList<Caja>{

        val resultado = db.rawQuery("SELECT * FROM equipo", null)
        val lista = ArrayList<Caja>()

        if (resultado!!.moveToFirst()){

            while (!resultado.isAfterLast){

                lista.add(findByIdCaja(resultado.getInt(1), db))
                resultado.moveToNext()

            }

        }

        return lista

    }

    fun updateEquipo(id: Int, idCaja: Int, db: SQLiteDatabase){

        val add = ContentValues()

        add.put("id_caja", idCaja)

        db.update("equipo", add, "id=$id", null)

    }

    fun findHora(db: SQLiteDatabase): String{

        val resultado = db.rawQuery("SELECT * FROM busqueda", null)

        if (resultado!!.moveToFirst()){

            return resultado.getString(1)

        }

        return ""

    }

    fun findbuscar(db: SQLiteDatabase): Int{

        val resultado = db.rawQuery("SELECT * FROM busqueda", null)

        if (resultado!!.moveToFirst()){

            return resultado.getInt(2)

        }

        return 0

    }

    fun updateBusqueda(hora: String, buscando: Int, db: SQLiteDatabase){

        val add = ContentValues()

        add.put("hora", hora)
        add.put("buscando", buscando)

        db.update("busqueda", add, "id=1", null)

    }

    fun addPokedex (id: String, db: SQLiteDatabase){

        val add = ContentValues()

        add.put("id", id)
        add.put("visible", 0)

        db.insert("pokedex", null, add)

    }

    fun findAllPokedex (db: SQLiteDatabase): ArrayList<Boolean>{

        val resultado = db.rawQuery("SELECT * FROM pokedex", null)
        val lista = ArrayList<Boolean>()

        if (resultado!!.moveToFirst()){

            while (!resultado.isAfterLast){

                if (resultado.getInt(1) == 0){

                    lista.add(false)

                }else{

                    lista.add(true)

                }
                resultado.moveToNext()

            }

        }

        return lista

    }

    fun visiblePokedex (id: String, db: SQLiteDatabase){

        val add = ContentValues()

        add.put("visible", 1)

        db.update("pokedex", add, "id='$id'", null)

    }

    fun findByIdPokedex (id: String, db: SQLiteDatabase): Boolean{

        val resultado = db.rawQuery("SELECT * FROM pokedex", null)

        if (resultado!!.moveToFirst()){

            while (!resultado.isAfterLast){

                if (resultado.getString(0) == id){

                    return resultado.getInt(1) != 0

                }

                resultado.moveToNext()

            }

        }

        return false

    }

    fun addMochila (mochila: Mochila, db: SQLiteDatabase){

        val add = ContentValues()

        add.put("id", mochila.id)
        add.put("id_objeto", mochila.objeto.id)
        add.put("cantidad", mochila.cantidad)

        db.insert("mochila", null, add)

    }

    fun findAllMochila (db: SQLiteDatabase): ArrayList<Mochila>{

        val resultado = db.rawQuery("SELECT * FROM mochila", null)
        val lista = ArrayList<Mochila>()

        if (resultado!!.moveToFirst()){

            while (!resultado.isAfterLast){

                lista.add(Mochila(resultado.getInt(0), objetoService.findById(resultado.getInt(1)), resultado.getInt(2)))
                resultado.moveToNext()

            }

        }

        return lista

    }

    fun findByObjetoMochila (id: Int, db: SQLiteDatabase): Mochila{

        val lista = findAllMochila(db)

        for (i in lista){
            if (i.objeto.id == id){

                return i

            }
        }

        return Mochila(-1, Objeto(0, "", 0), 0)

    }

    fun updateMochila (mochila: Mochila, cantidad: Int, db: SQLiteDatabase){

        val add = ContentValues()
        val numero = mochila.cantidad + cantidad

        add.put("cantidad", numero)

        db.update("mochila", add, "id=${mochila.id}", null)

    }

    private fun addEquipo (id: Int, idCaja: Int, db: SQLiteDatabase){

        val add = ContentValues()

        add.put("id", id)
        add.put("id_caja", idCaja)

        db.insert("equipo", null, add)

    }

    private fun addBusqueda (db: SQLiteDatabase){

        val add = ContentValues()

        add.put("id", 1)
        add.put("hora", "")
        add.put("buscando", 0)

        db.insert("busqueda", null, add)

    }

}