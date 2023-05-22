package com.isma.criapokemon


import android.annotation.SuppressLint
import android.content.Intent
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
import com.isma.criapokemon.service.impl.*
import com.isma.criapokemon.variablesdrawable.VariablesImgPokemons
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class RecompensasActivity : AppCompatActivity() {

    val busquedaService = BusquedaServiceImpl(this)
    val recompensasService = RecompensasServiceImpl(this)
    val cajaService = CajaServiceImpl(this)
    val equipoService = EquipoServiceImpl(this)
    val pokedexService = PokedexServiceImpl(this)
    val variablesImgPokemons = VariablesImgPokemons()
    var recompensa = Recompensas(Pokemon("", "", "", "", "", ""), 0)
    var contador = 0
    var caja = ArrayList<Caja>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recompensas)

        val texto = findViewById<TextView>(R.id.textorecompensa)
        val img = findViewById<ImageView>(R.id.recompensa)
        val recojer = findViewById<Button>(R.id.recojer)
        val rechazar = findViewById<Button>(R.id.rechazar)
        val recompensas = calcula()

        caja = cajaService.descodificar(intent.getStringExtra("caja").toString())

        if (recompensas.size == 0){

            val bmp = BitmapFactory.decodeResource(resources, variablesImgPokemons.img(recompensa.pokemon.img))

            img.setImageBitmap(bmp)
            texto.setText("no hay recompensas")
            recojer.visibility = View.GONE
            rechazar.setText("salir")
            rechazar.setOnClickListener{

                finish()
                startActivity(Intent(this, MainActivity::class.java))

            }

        }else{

            var numero = 0

            recompensa = recompensas[contador]

            for (i in caja){
                if (i.pokemon.id == recompensa.pokemon.id){

                    numero++

                }
            }

            val bmp = BitmapFactory.decodeResource(resources, variablesImgPokemons.img(recompensa.pokemon.img))

            equipoService.subidaNivel()
            img.setImageBitmap(bmp)
            texto.setText(recompensa.pokemon.name+" tienes "+numero)
            recojer.setOnClickListener {

                val i = Caja(caja[caja.size-1].id+1, recompensa.pokemon.name, recompensa.pokemon)

                cajaService.add(i)
                caja.add(i)
                pokedexService.visible(recompensa.pokemon.id)
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
        val recompensasTotales = (diferencia / 10).toInt()
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

                if (recompensas.size == 50){

                    return recompensas

                }

            }
        }

        return recompensas

    }

    private fun fin(recompensas: ArrayList<Recompensas>, img: ImageView, texto: TextView){

        if (contador < recompensas.size-1){

            var numero = 0

            contador++
            recompensa = recompensas[contador]

            for (i in caja){
                if (i.pokemon.id == recompensa.pokemon.id){

                    numero++

                }
            }

            val bmp = BitmapFactory.decodeResource(resources, variablesImgPokemons.img(recompensa.pokemon.img))

            img.setImageBitmap(bmp)
            texto.setText(recompensa.pokemon.name+" tienes "+numero)

        }else{

            finish()
            startActivity(Intent(this, RecompensasObjetosActivity::class.java))

        }

    }

}