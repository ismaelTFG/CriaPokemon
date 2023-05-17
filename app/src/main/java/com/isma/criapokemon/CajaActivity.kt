package com.isma.criapokemon

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.*
import com.isma.criapokemon.entity.Caja
import com.isma.criapokemon.entity.Pokemon
import com.isma.criapokemon.service.impl.CajaServiceImpl
import com.isma.criapokemon.variablesdrawable.ColoresTipos
import com.isma.criapokemon.variablesdrawable.VariablesImgPokemons
import java.util.zip.Inflater

class CajaActivity : AppCompatActivity() {

    private val mainActivity = MainActivity()
    private val cajaService = CajaServiceImpl(this)
    private val variablesImgPokemons = VariablesImgPokemons()
    private val coloresTipos = ColoresTipos()
    private var numero = 0
    private var mostrado = Caja(0, "", Pokemon("", "", "", "", "", ""))

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caja)

        val apodoView = findViewById<TextView>(R.id.apodo)
        val descripcion = findViewById<TextView>(R.id.descripcion)
        val img = findViewById<ImageView>(R.id.imgpoke)
        val opciones = findViewById<Button>(R.id.opciones)
        val evolucion = findViewById<Button>(R.id.evolucion)
        val anterior = findViewById<ImageButton>(R.id.anterior)
        val siguiente = findViewById<ImageButton>(R.id.siguiente)
        val cajas = cajaService.findAll()

        numero = intent.extras?.getInt("numero")!!
        mostrado = cajas[numero]

        if (cajaService.evoTrue(mostrado)){

            evolucion.visibility = View.VISIBLE

        }else{

            evolucion.visibility = View.GONE

        }

        apodoView.setText(mostrado.apodo)
        apodoView.setBackgroundResource(coloresTipos.colores(mostrado.pokemon.tipoUno, mostrado.pokemon.tipoDos))
        descripcion.setText(mostrado.toString())
        img.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(mostrado.pokemon.img)))
        opciones.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.opciones_caja, null)
            val cambiarApodo = dialogLayout.findViewById<Button>(R.id.cambiarapodo)
            val liberar = dialogLayout.findViewById<Button>(R.id.liberar)

            cambiarApodo.setOnClickListener {
                cambioApodo(cajas, apodoView, descripcion, img)
            }
            liberar.setOnClickListener {
                liberar()
            }
            builder.setTitle("Seleccione la opcion")
            builder.setView(dialogLayout)
            builder.show()

        }
        anterior.setOnClickListener {

            if (numero == 0){

                numero = cajas.size -1
                mostrado = cajas[numero]

                if (cajaService.evoTrue(mostrado)){

                    evolucion.visibility = View.VISIBLE

                }else{

                    evolucion.visibility = View.GONE

                }

                apodoView.setText(mostrado.apodo)
                apodoView.setBackgroundResource(coloresTipos.colores(mostrado.pokemon.tipoUno, mostrado.pokemon.tipoDos))
                descripcion.setText(mostrado.toString())
                img.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(mostrado.pokemon.img)))

            }else{

                numero--
                mostrado = cajas[numero]

                if (cajaService.evoTrue(mostrado)){

                    evolucion.visibility = View.VISIBLE

                }else{

                    evolucion.visibility = View.GONE

                }

                apodoView.setText(mostrado.apodo)
                apodoView.setBackgroundResource(coloresTipos.colores(mostrado.pokemon.tipoUno, mostrado.pokemon.tipoDos))
                descripcion.setText(mostrado.toString())
                img.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(mostrado.pokemon.img)))

            }

        }
        siguiente.setOnClickListener {

            if (numero == (cajas.size - 1)){

                numero = 0
                mostrado = cajas[numero]

                if (cajaService.evoTrue(mostrado)){

                    evolucion.visibility = View.VISIBLE

                }else{

                    evolucion.visibility = View.GONE

                }

                apodoView.setText(mostrado.apodo)
                apodoView.setBackgroundResource(coloresTipos.colores(mostrado.pokemon.tipoUno, mostrado.pokemon.tipoDos))
                descripcion.setText(mostrado.toString())
                img.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(mostrado.pokemon.img)))

            }else{

                numero++
                mostrado = cajas[numero]

                if (cajaService.evoTrue(mostrado)){

                    evolucion.visibility = View.VISIBLE

                }else{

                    evolucion.visibility = View.GONE

                }

                apodoView.setText(mostrado.apodo)
                apodoView.setBackgroundResource(coloresTipos.colores(mostrado.pokemon.tipoUno, mostrado.pokemon.tipoDos))
                descripcion.setText(mostrado.toString())
                img.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(mostrado.pokemon.img)))

            }

        }
        evolucion.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.spinner_pokemons, null)
            val spinner = dialogLayout.findViewById<Spinner>(R.id.spinnerpokemons)

            spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cajaService.viewEvoluciones(mostrado))
            builder.setTitle("Elige como quieres evolucionarlo")
            builder.setView(dialogLayout)
            builder.setPositiveButton("Seleccionar"){ dialogInterface, i ->

                val selecionado = spinner.selectedItemPosition

                cajaService.evolucion(mostrado, selecionado, mainActivity)
                mostrado = cajas[numero]

                if (cajaService.evoTrue(mostrado)){

                    evolucion.visibility = View.VISIBLE

                }else{

                    evolucion.visibility = View.GONE

                }

                apodoView.setText(mostrado.apodo)
                apodoView.setBackgroundResource(coloresTipos.colores(mostrado.pokemon.tipoUno, mostrado.pokemon.tipoDos))
                descripcion.setText(mostrado.toString())
                img.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(mostrado.pokemon.img)))

                dialogInterface.dismiss()

            }
            builder.show()

        }

    }

    fun salir(view: View){

        finish()
        startActivity(Intent(this, PokemonActivity::class.java))

    }

    private fun cambioApodo(cajas: ArrayList<Caja>, apodoView: TextView, descripcion: TextView, img: ImageView){

        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.edittext_pokemon, null)
        val apodo = dialogLayout.findViewById<EditText>(R.id.apodo)

        apodo.hint = mostrado.apodo
        builder.setTitle("Escribe el nuevo apodo")
        builder.setView(dialogLayout)
        builder.setPositiveButton("aceptar"){ dialogInterface, _ ->

            val texto = apodo.text.toString()

            if (texto == ""){

                val caja = mostrado

                caja.apodo = mostrado.pokemon.name
                cajaService.update(caja)

                val cajas = cajaService.findAll()

                mostrado = cajas[numero]

                apodoView.setText(mostrado.apodo)
                descripcion.setText(mostrado.toString())
                img.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(mostrado.pokemon.img)))

            }else{

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

            }

            dialogInterface.dismiss()

        }
        builder.show()

    }
    private fun liberar(){

        val dialog = AlertDialog.Builder(this).setPositiveButton("si", DialogInterface.OnClickListener{ dialogInterface, i ->
            cajaService.delete(mostrado.id)
            Toast.makeText(this, "El pokemon a vuelto a la naturaleza", Toast.LENGTH_SHORT).show()
            finish()
            startActivity(Intent(this, PokemonActivity::class.java))
            dialogInterface.dismiss()
        }).setNegativeButton("no", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.dismiss()
        }).setTitle("Advertencia").setMessage("Si liberas el pokemon nunca lo podras recuperar ¿estas seguro?").create()

        dialog.show()

    }

}