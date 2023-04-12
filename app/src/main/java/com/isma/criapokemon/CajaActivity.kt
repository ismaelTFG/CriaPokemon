package com.isma.criapokemon

import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.*
import com.isma.criapokemon.entity.Caja
import com.isma.criapokemon.entity.Pokemon
import com.isma.criapokemon.service.impl.CajaServiceImpl
import com.isma.criapokemon.variablesdrawable.VariablesImgPokemons

class CajaActivity : AppCompatActivity() {

    private val cajaService = CajaServiceImpl(this)
    private val variablesImgPokemons = VariablesImgPokemons()
    private var numero = 0
    private var mostrado = Caja(0, "", Pokemon("", "", "", "", ""))

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caja)

        val apodoView = findViewById<TextView>(R.id.apodo)
        val descripcion = findViewById<TextView>(R.id.descripcion)
        val img = findViewById<ImageView>(R.id.imgpoke)
        val cambiarApodo = findViewById<Button>(R.id.cambiarapodo)
        val anterior = findViewById<ImageButton>(R.id.anterior)
        val siguiente = findViewById<ImageButton>(R.id.siguiente)
        val cajas = cajaService.findAll()

        mostrado = cajas[numero]

        apodoView.setText(mostrado.apodo)
        descripcion.setText(mostrado.toString())
        img.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(mostrado.pokemon.img)))
        cambiarApodo.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.edittext_pokemon, null)
            val apodo = dialogLayout.findViewById<EditText>(R.id.apodo)

            apodo.hint = mostrado.apodo
            builder.setTitle("Escribe el nuevo apodo")
            builder.setView(dialogLayout)
            builder.setPositiveButton("aceptar"){ dialogInterface, i ->

                val texto = apodo.text.toString()
                var rep = false

                for (i in cajas){
                    if (i.apodo == texto){

                        rep = true

                    }
                }

                if (!rep){

                    val caja = mostrado

                    caja.apodo = texto
                    cajaService.update(caja)

                    val cajas = cajaService.findAll()

                    mostrado = cajas[numero]

                    apodoView.setText(mostrado.apodo)
                    descripcion.setText(mostrado.toString())
                    img.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(mostrado.pokemon.img)))

                }else{

                    Toast.makeText(this, "no puedes usar ese apodo", Toast.LENGTH_LONG).show()

                }

                dialogInterface.dismiss()

            }
            builder.show()

        }


    }

    fun salir(view: View){

        finish()

    }

}