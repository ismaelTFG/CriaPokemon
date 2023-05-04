package com.isma.criapokemon.repository

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.isma.criapokemon.entity.*

class Sqlite(context: Context): SQLiteOpenHelper(context, "criapokemon", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("CREATE TABLE pokemon (id TEXT PRIMARY KEY, name TEXT, img TEXT, tipo1 TEXT, tipo2 TEXT, evolucion TEXT)")
        db.execSQL("CREATE TABLE caja (id INTEGER PRIMARY KEY, apodo TEXT, id_pokemon TEXT, nivel INTEGER, genero INTEGER, FOREIGN KEY (id_pokemon) REFERENCES pokemon(id))")
        db.execSQL("CREATE TABLE equipo (id INTEGER PRIMARY KEY, id_caja INTEGER)")
        db.execSQL("CREATE TABLE busqueda (id INTEGER PRIMARY KEY, hora TEXT, buscando INTEGER)")
        db.execSQL("CREATE TABLE recompensas (id INTEGER PRIMARY KEY, porcentaje INTEGER)")
        db.execSQL("CREATE TABLE pokedex (id TEXT PRIMARY KEY, visible INTEGER)")
        db.execSQL("CREATE TABLE objetos (id INTEGER PRIMARY KEY, nombre TEXT, porcentaje INTEGER)")
        db.execSQL("CREATE TABLE mochila (id INTEGER PRIMARY KEY, id_objeto INTEGER, cantidad INTEGER)")

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
        add.put("evolucion", pokemon.evolucion)

        db.insert("pokemon", null, add)

    }

    fun findAllPokemon (db: SQLiteDatabase): ArrayList<Pokemon>{

        val resultado = db.rawQuery("SELECT * FROM pokemon", null)
        val lista = ArrayList<Pokemon>()

        if (resultado!!.moveToFirst()){

            while (!resultado.isAfterLast){

                val pokemon = Pokemon(resultado.getString(0), resultado.getString(1), resultado.getString(2), resultado.getString(3), resultado.getString(4), resultado.getString(5))

                lista.add(pokemon)
                resultado.moveToNext()

            }

        }

        return lista;

    }

    fun findByIdPokemon (id: String, db: SQLiteDatabase): Pokemon{

        val lista = findAllPokemon(db)
        var pokemon = Pokemon("", "", "", "", "", "")

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

        return Caja(0, "", Pokemon("", "", "", "", "", ""))

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
        add.put("id_pokemon", caja.pokemon.id)
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

        return Caja(0, "", Pokemon("", "", "", "", "", ""))

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

    fun addObjetos (objeto: Objeto, db: SQLiteDatabase){

        val add = ContentValues()

        add.put("id", objeto.id)
        add.put("nombre", objeto.nombre)
        add.put("porcentaje", objeto.porcentaje)

        db.insert("objetos", null, add)

    }

    fun findAllObjetos (db: SQLiteDatabase): ArrayList<Objeto>{

        val resultado = db.rawQuery("SELECT * FROM objetos", null)
        val lista = ArrayList<Objeto>()

        if (resultado!!.moveToFirst()){

            while (!resultado.isAfterLast){

                lista.add(Objeto(resultado.getInt(0), resultado.getString(1), resultado.getInt(2)))
                resultado.moveToNext()

            }

        }

        return lista

    }

    fun findByIdObjetos (id: Int, db: SQLiteDatabase): Objeto{

        val lista = findAllObjetos(db)

        for (i in lista){
            if (i.id == id){

                return i

            }
        }

        return Objeto(0, "", 0)

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

                lista.add(Mochila(resultado.getInt(0), findByIdObjetos(resultado.getInt(1), db), resultado.getInt(2)))
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