package com.isma.criapokemon

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.isma.criapokemon.entity.Objeto
import com.isma.criapokemon.service.impl.BusquedaServiceImpl
import com.isma.criapokemon.service.impl.MochilaServiceImpl
import com.isma.criapokemon.service.impl.ObjetoServiceImpl
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

class RecompensasObjetosActivity : AppCompatActivity() {

    private val busquedaService = BusquedaServiceImpl(this)
    private val objetoService = ObjetoServiceImpl(this)
    private val mochilaService = MochilaServiceImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recompensas_objetos)

        val objetosView = findViewById<TextView>(R.id.objetos)
        val objetos = objetos()

        busquedaService.update("", false)
        objetosView.setText(mostrar(objetos))

        for (i in objetos){

            mochilaService.add(i)

        }

    }

    fun salir(view: View){

        finish()

    }

    private fun objetos(): ArrayList<Objeto>{

        val objetos = ArrayList<Objeto>()
        val actual = LocalDateTime.now()
        val iniciada = LocalDateTime.parse(busquedaService.hora())
        val diferencia = ChronoUnit.MINUTES.between(iniciada, actual)
        val recompensasTotales = (diferencia / 10).toInt()
        val lista = objetoService.listAll()

        if (recompensasTotales > 0){
            for (i in 1..recompensasTotales){

                var salida = true

                while (salida){

                    val numero = (Math.random() * lista.size).toInt()
                    val recompensa = lista[numero]
                    val porcentaje = Math.random() * 100

                    if (recompensa.porcentaje >= porcentaje){

                        objetos.add(recompensa)
                        salida = false

                    }

                }

            }
        }

        return objetos

    }

    private fun mostrar(objetos: ArrayList<Objeto>): String{

        var exit = ""

        for (i in objetos){

            exit = exit + i.nombre + ", "

        }

        return exit

    }

}