package com.isma.criapokemon.repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.isma.criapokemon.entity.Caja
import com.isma.criapokemon.entity.Pokemon
import com.isma.criapokemon.entity.Recompensas

class Sqlite(context: Context): SQLiteOpenHelper(context, "criapokemon", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE pokemon (id TEXT PRIMARY KEY, name TEXT, img TEXT, tipo1 TEXT, tipo2 TEXT)")
        db.execSQL("CREATE TABLE caja (id INTEGER PRIMARY KEY, apodo TEXT, id_pokemon TEXT, nivel INTEGER, genero INTEGER, FOREIGN KEY (id_pokemon) REFERENCES pokemon(id))")
        db.execSQL("CREATE TABLE equipo (id INTEGER PRIMARY KEY, id_caja INTEGER)")
        db.execSQL("CREATE TABLE busqueda (id INTEGER PRIMARY KEY, hora TEXT, buscando INTEGER)")
        db.execSQL("CREATE TABLE recompensas (id INTEGER PRIMARY KEY, porcentaje INTEGER)")

        addBusqueda(db)
        for (i in 1..6){

            addEquipo(i, -10+i, db)

        }

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addPokemon (pokemon: Pokemon, db: SQLiteDatabase){

        val add = ContentValues()

        add.put("id", pokemon.id)
        add.put("name", pokemon.name)
        add.put("img", pokemon.img)
        add.put("tipo1", pokemon.tipoUno)
        add.put("tipo2", pokemon.tipoDos)

        db.insert("pokemon", null, add)

    }

    fun findAllPokemon (db: SQLiteDatabase): ArrayList<Pokemon>{

        val resultado = db.rawQuery("SELECT * FROM pokemon", null)
        val lista = ArrayList<Pokemon>()

        if (resultado!!.moveToFirst()){

            while (!resultado.isAfterLast){

                val pokemon = Pokemon(resultado.getString(0), resultado.getString(1), resultado.getString(2), resultado.getString(3), resultado.getString(4))

                lista.add(pokemon)
                resultado.moveToNext()

            }

        }

        return lista;

    }

    fun findByIdPokemon (id: String, db: SQLiteDatabase): Pokemon{

        val lista = findAllPokemon(db)
        var pokemon = Pokemon("", "", "", "", "")

        for(i in lista){
            if(i.id == id){

                pokemon = i

            }
        }

        return pokemon

    }

    fun findAllCaja (db: SQLiteDatabase): ArrayList<Caja>{

        val resultado = db.rawQuery("SELECT * FROM caja", null)
        val lista = ArrayList<Caja>()

        if (resultado!!.moveToFirst()){

            while (!resultado.isAfterLast){

                val caja = Caja(resultado.getInt(0), resultado.getString(1), findByIdPokemon(resultado.getString(2), db))

                caja.setNivel(resultado.getInt(3))
                caja.setMacho(resultado.getInt(4))

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

        return Caja(0, "", Pokemon("", "", "", "", ""))

    }

    fun addCaja (caja: Caja, db: SQLiteDatabase){

        val add = ContentValues()

        add.put("id", caja.id)
        add.put("apodo", caja.apodo)
        add.put("id_pokemon", caja.pokemon.id)
        add.put("nivel", caja.getNivel())
        add.put("genero", caja.getGenero())

        db.insert("caja", null, add)

    }

    fun updateCaja (caja: Caja, db: SQLiteDatabase){

        val add = ContentValues()

        add.put("apodo", caja.apodo)
        add.put("nivel", caja.getNivel())

        db.update("caja", add, "id=${caja.id}", null)

    }

    fun findByIdCaja (id: Int, db: SQLiteDatabase): Caja{

        val lista = findAllCaja(db)

        for (i in lista){
            if (i.id == id){

                return i

            }
        }

        return Caja(0, "", Pokemon("", "", "", "", ""))

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

    fun addRecompensas (id: Int, porcentaje: Int, db: SQLiteDatabase){

        val add = ContentValues()

        add.put("id", id)
        add.put("porcentaje", porcentaje)

        db.insert("recompensas", null, add)

    }

    fun findAllRecompensas (db: SQLiteDatabase): ArrayList<Recompensas>{

        val resultado = db.rawQuery("SELECT * FROM recompensas", null)
        val lista = ArrayList<Recompensas>()

        if (resultado!!.moveToFirst()){

            while (!resultado.isAfterLast){

                lista.add(Recompensas(findByIdPokemon(""+resultado.getInt(0), db), resultado.getInt(1)))
                resultado.moveToNext()

            }

        }

        return lista

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