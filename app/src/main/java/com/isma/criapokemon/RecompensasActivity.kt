package com.isma.criapokemon


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.isma.criapokemon.entity.Caja
import com.isma.criapokemon.entity.Pokemon
import com.isma.criapokemon.entity.Recompensas
import com.isma.criapokemon.service.impl.BusquedaServiceImpl
import com.isma.criapokemon.service.impl.CajaServiceImpl
import com.isma.criapokemon.service.impl.EquipoServiceImpl
import com.isma.criapokemon.service.impl.RecompensasServiceImpl
import com.isma.criapokemon.variablesdrawable.VariablesImgPokemons
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class RecompensasActivity : AppCompatActivity() {

    val busquedaService = BusquedaServiceImpl(this)
    val recompensasService = RecompensasServiceImpl(this)
    val cajaService = CajaServiceImpl(this)
    val equipoService = EquipoServiceImpl(this)
    val variablesImgPokemons = VariablesImgPokemons()
    var recompensa = Recompensas(Pokemon("", "", "", "", ""), 0)
    var contador = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recompensas)

        val texto = findViewById<TextView>(R.id.textorecompensa)
        val img = findViewById<ImageView>(R.id.recompensa)
        val recojer = findViewById<Button>(R.id.recojer)
        val rechazar = findViewById<Button>(R.id.rechazar)
        val recompensas = calcula()

        if (recompensas.size == 0){

            val bmp = BitmapFactory.decodeResource(resources, variablesImgPokemons.img(recompensa.pokemon.img))

            img.setImageBitmap(bmp)
            texto.setText("no hay recompensas")
            recojer.visibility = View.GONE
            rechazar.setText("salir")
            rechazar.setOnClickListener{

                finish()

            }

        }else{

            recompensa = recompensas[contador]

            val bmp = BitmapFactory.decodeResource(resources, variablesImgPokemons.img(recompensa.pokemon.img))

            equipoService.subidaNivel()
            busquedaService.update("", false)
            img.setImageBitmap(bmp)
            texto.setText(recompensa.pokemon.name)
            recojer.setOnClickListener {

                cajaService.add(Caja(cajaService.findAll().size+2, recompensa.pokemon.name, recompensa.pokemon))
                Toast.makeText(this, "has guardado a "+recompensa.pokemon.name, Toast.LENGTH_SHORT).show()
                fin(recompensas, img, texto)

            }
            rechazar.setOnClickListener {

                Toast.makeText(this, recompensa.pokemon.name+" ha vuelto a la naturaleza", Toast.LENGTH_SHORT).show()
                fin(recompensas, img, texto)

            }

        }

    }

    private fun calcula(): ArrayList<Recompensas>{

        val recompensas = ArrayList<Recompensas>()
        val actual = LocalDateTime.now()
        val iniciada = LocalDateTime.parse(busquedaService.hora())
        val diferencia = ChronoUnit.MINUTES.between(iniciada, actual)
        val recompensasTotales = (diferencia / 5).toInt()
        val lista = recompensasService.listAll()

        if (recompensasTotales > 0){
            for (i in 1..recompensasTotales){

                var salida = true

                while (salida){

                    val numero = (Math.random() * lista.size).toInt()
                    val recompensa = lista[numero]
                    val porcentaje = Math.random() * 100

                    if (recompensa.porcentaje >= porcentaje){

                        recompensas.add(recompensa)
                        salida = false

                    }

                }

            }
        }

        return recompensas

    }

    private fun fin(recompensas: ArrayList<Recompensas>, img: ImageView, texto: TextView){

        if (contador < recompensas.size-1){

            contador++
            recompensa = recompensas[contador]

            val bmp = BitmapFactory.decodeResource(resources, variablesImgPokemons.img(recompensa.pokemon.img))

            img.setImageBitmap(bmp)
            texto.setText(recompensa.pokemon.name)

        }else{

            finish()

        }

    }

}