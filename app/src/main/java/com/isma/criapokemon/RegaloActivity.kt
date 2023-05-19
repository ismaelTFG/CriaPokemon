package com.isma.criapokemon

import android.content.Intent
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.isma.criapokemon.entity.Caja
import com.isma.criapokemon.entity.Pokemon
import com.isma.criapokemon.service.impl.CajaServiceImpl
import com.isma.criapokemon.service.impl.PokedexServiceImpl
import com.isma.criapokemon.service.impl.PokemonServiceImpl
import com.isma.criapokemon.variablesdrawable.VariablesImgPokemons

class RegaloActivity : AppCompatActivity() {

    val pokedexService = PokedexServiceImpl(this)
    val pokemonService = PokemonServiceImpl(this)
    val cajaService = CajaServiceImpl(this)
    val variablesImgPokemons = VariablesImgPokemons()
    var caja = ArrayList<Caja>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regalo)

        val articuno = findViewById<ImageButton>(R.id.articuno)
        val zapdos = findViewById<ImageButton>(R.id.zapdos)
        val moltres = findViewById<ImageButton>(R.id.moltres)
        val mewtwo = findViewById<ImageButton>(R.id.mewtwo)
        val mew = findViewById<ImageButton>(R.id.mew)
        val artidos = findViewById<ImageButton>(R.id.artidos)
        val artitres = findViewById<ImageButton>(R.id.artitres)
        val artitwo = findViewById<ImageButton>(R.id.artitwo)
        val artimew = findViewById<ImageButton>(R.id.artimew)
        val zaptres = findViewById<ImageButton>(R.id.zaptres)
        val zaptwo = findViewById<ImageButton>(R.id.zaptwo)
        val zapmew = findViewById<ImageButton>(R.id.zapmew)
        val moltwo = findViewById<ImageButton>(R.id.moltwo)
        val molmew = findViewById<ImageButton>(R.id.molmew)
        val mewthree = findViewById<ImageButton>(R.id.mewthree)
        val ditto = findViewById<ImageButton>(R.id.ditto)

        caja = cajaService.descodificar(intent.getStringExtra("caja").toString())

        mostrar(articuno, pokemonService.findById("144"))
        mostrar(zapdos, pokemonService.findById("145"))
        mostrar(moltres, pokemonService.findById("146"))
        mostrar(mewtwo, pokemonService.findById("150"))
        mostrar(mew, pokemonService.findById("151"))
        mostrar(artidos, pokemonService.findById("144_145"))
        mostrar(artitres, pokemonService.findById("144_146"))
        mostrar(artitwo, pokemonService.findById("144_150"))
        mostrar(artimew, pokemonService.findById("144_151"))
        mostrar(zaptres, pokemonService.findById("145_146"))
        mostrar(zaptwo, pokemonService.findById("145_150"))
        mostrar(zapmew, pokemonService.findById("145_151"))
        mostrar(moltwo, pokemonService.findById("146_150"))
        mostrar(molmew, pokemonService.findById("146_151"))
        mostrar(mewthree, pokemonService.findById("150_151"))
        mostrar(ditto, pokemonService.findById("132"))

    }

    fun salir(view: View){

        finish()
        startActivity(Intent(this, MainActivity::class.java))

    }

    private fun mostrar(boton: ImageButton, pokemon: Pokemon){

        val especies = pokedexService.especies()

        when(pokemon.id){
            "132" -> {

                if (especies >= 1361){
                    if (pokemon.id != cajaService.findByidPokemon(pokemon.id)[0].pokemon.id){

                        boton.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(pokemon.img)))
                        boton.setBackgroundResource(R.drawable.electrico)
                        boton.setOnClickListener {

                            cajaService.add(Caja(caja[caja.size-1].id+1, pokemon.name, pokemon))
                            pokedexService.visible(pokemon.id)
                            finish()
                            startActivity(Intent(this, MainActivity::class.java))

                        }

                    }else{

                        boton.visibility = View.GONE

                    }
                }else{

                    boton.visibility = View.GONE

                }

            }
            "144" -> {

                if (especies >= 29){
                    if (pokemon.id != cajaService.findByidPokemon(pokemon.id)[0].pokemon.id){

                        boton.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(pokemon.img)))
                        boton.setBackgroundResource(R.drawable.electrico)
                        boton.setOnClickListener {

                            cajaService.add(Caja(caja[caja.size-1].id+1, pokemon.name, pokemon))
                            pokedexService.visible(pokemon.id)
                            finish()
                            startActivity(Intent(this, MainActivity::class.java))

                        }

                    }else{

                        boton.visibility = View.GONE

                    }
                }else{

                    boton.visibility = View.GONE

                }

            }
            "145" -> {

                if (especies >= 58){
                    if (pokemon.id != cajaService.findByidPokemon(pokemon.id)[0].pokemon.id){

                        boton.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(pokemon.img)))
                        boton.setBackgroundResource(R.drawable.electrico)
                        boton.setOnClickListener {

                            cajaService.add(Caja(caja[caja.size-1].id+1, pokemon.name, pokemon))
                            pokedexService.visible(pokemon.id)
                            finish()
                            startActivity(Intent(this, MainActivity::class.java))

                        }

                    }else{

                        boton.visibility = View.GONE

                    }
                }else{

                    boton.visibility = View.GONE

                }

            }
            "146" -> {

                if (especies >= 87){
                    if (pokemon.id != cajaService.findByidPokemon(pokemon.id)[0].pokemon.id){

                        boton.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(pokemon.img)))
                        boton.setBackgroundResource(R.drawable.electrico)
                        boton.setOnClickListener {

                            cajaService.add(Caja(caja[caja.size-1].id+1, pokemon.name, pokemon))
                            pokedexService.visible(pokemon.id)
                            finish()
                            startActivity(Intent(this, MainActivity::class.java))

                        }

                    }else{

                        boton.visibility = View.GONE

                    }
                }else{

                    boton.visibility = View.GONE

                }

            }
            "150" -> {

                if (especies >= 116){
                    if (pokemon.id != cajaService.findByidPokemon(pokemon.id)[0].pokemon.id){

                        boton.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(pokemon.img)))
                        boton.setBackgroundResource(R.drawable.electrico)
                        boton.setOnClickListener {

                            cajaService.add(Caja(caja[caja.size-1].id+1, pokemon.name, pokemon))
                            pokedexService.visible(pokemon.id)
                            finish()
                            startActivity(Intent(this, MainActivity::class.java))

                        }

                    }else{

                        boton.visibility = View.GONE

                    }
                }else{

                    boton.visibility = View.GONE

                }

            }
            "151" -> {

                if (especies >= 145){
                    if (pokemon.id != cajaService.findByidPokemon(pokemon.id)[0].pokemon.id){

                        boton.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(pokemon.img)))
                        boton.setBackgroundResource(R.drawable.electrico)
                        boton.setOnClickListener {

                            cajaService.add(Caja(caja[caja.size-1].id+1, pokemon.name, pokemon))
                            pokedexService.visible(pokemon.id)
                            finish()
                            startActivity(Intent(this, MainActivity::class.java))

                        }

                    }else{

                        boton.visibility = View.GONE

                    }
                }else{

                    boton.visibility = View.GONE

                }

            }
            "144_145" -> {

                if (especies >= 134){
                    if (pokemon.id != cajaService.findByidPokemon(pokemon.id)[0].pokemon.id){

                        boton.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(pokemon.img)))
                        boton.setBackgroundResource(R.drawable.electrico)
                        boton.setOnClickListener {

                            cajaService.add(Caja(caja[caja.size-1].id+1, pokemon.name, pokemon))
                            pokedexService.visible(pokemon.id)
                            finish()
                            startActivity(Intent(this, MainActivity::class.java))

                        }

                    }else{
                        boton.visibility = View.GONE
                    }
                }else{

                    boton.visibility = View.GONE

                }

            }
            "144_146" -> {

                if (especies >= 269){
                    if (pokemon.id != cajaService.findByidPokemon(pokemon.id)[0].pokemon.id){

                        boton.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(pokemon.img)))
                        boton.setBackgroundResource(R.drawable.electrico)
                        boton.setOnClickListener {

                            cajaService.add(Caja(caja[caja.size-1].id+1, pokemon.name, pokemon))
                            pokedexService.visible(pokemon.id)
                            finish()
                            startActivity(Intent(this, MainActivity::class.java))

                        }

                    }else{

                        boton.visibility = View.GONE

                    }
                }else{

                    boton.visibility = View.GONE

                }

            }
            "144_150" -> {

                if (especies >= 403){
                    if (pokemon.id != cajaService.findByidPokemon(pokemon.id)[0].pokemon.id){

                        boton.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(pokemon.img)))
                        boton.setBackgroundResource(R.drawable.electrico)
                        boton.setOnClickListener {

                            cajaService.add(Caja(caja[caja.size-1].id+1, pokemon.name, pokemon))
                            pokedexService.visible(pokemon.id)
                            finish()
                            startActivity(Intent(this, MainActivity::class.java))

                        }

                    }else{

                        boton.visibility = View.GONE

                    }
                }else{

                    boton.visibility = View.GONE

                }

            }
            "144_151" -> {

                if (especies >= 538){
                    if (pokemon.id != cajaService.findByidPokemon(pokemon.id)[0].pokemon.id){

                        boton.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(pokemon.img)))
                        boton.setBackgroundResource(R.drawable.electrico)
                        boton.setOnClickListener {

                            cajaService.add(Caja(caja[caja.size-1].id+1, pokemon.name, pokemon))
                            pokedexService.visible(pokemon.id)
                            finish()
                            startActivity(Intent(this, MainActivity::class.java))

                        }

                    }else{

                        boton.visibility = View.GONE

                    }
                }else{

                    boton.visibility = View.GONE

                }

            }
            "145_146" -> {

                if (especies >= 673){
                    if (pokemon.id != cajaService.findByidPokemon(pokemon.id)[0].pokemon.id){

                        boton.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(pokemon.img)))
                        boton.setBackgroundResource(R.drawable.electrico)
                        boton.setOnClickListener {

                            cajaService.add(Caja(caja[caja.size-1].id+1, pokemon.name, pokemon))
                            pokedexService.visible(pokemon.id)
                            finish()
                            startActivity(Intent(this, MainActivity::class.java))

                        }

                    }else{

                        boton.visibility = View.GONE

                    }
                }else{

                    boton.visibility = View.GONE

                }

            }
            "145_150" -> {

                if (especies >= 807){
                    if (pokemon.id != cajaService.findByidPokemon(pokemon.id)[0].pokemon.id){

                        boton.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(pokemon.img)))
                        boton.setBackgroundResource(R.drawable.electrico)
                        boton.setOnClickListener {

                            cajaService.add(Caja(caja[caja.size-1].id+1, pokemon.name, pokemon))
                            pokedexService.visible(pokemon.id)
                            finish()
                            startActivity(Intent(this, MainActivity::class.java))

                        }

                    }else{

                        boton.visibility = View.GONE

                    }
                }else{

                    boton.visibility = View.GONE

                }

            }
            "145_151" -> {

                if (especies >= 942){
                    if (pokemon.id != cajaService.findByidPokemon(pokemon.id)[0].pokemon.id){

                        boton.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(pokemon.img)))
                        boton.setBackgroundResource(R.drawable.electrico)
                        boton.setOnClickListener {

                            cajaService.add(Caja(caja[caja.size-1].id+1, pokemon.name, pokemon))
                            pokedexService.visible(pokemon.id)
                            finish()
                            startActivity(Intent(this, MainActivity::class.java))

                        }

                    }else{

                        boton.visibility = View.GONE

                    }
                }else{

                    boton.visibility = View.GONE

                }

            }
            "146_150" -> {

                if (especies >= 1076){
                    if (pokemon.id != cajaService.findByidPokemon(pokemon.id)[0].pokemon.id){

                        boton.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(pokemon.img)))
                        boton.setBackgroundResource(R.drawable.electrico)
                        boton.setOnClickListener {

                            cajaService.add(Caja(caja[caja.size-1].id+1, pokemon.name, pokemon))
                            pokedexService.visible(pokemon.id)
                            finish()
                            startActivity(Intent(this, MainActivity::class.java))

                        }

                    }else{

                        boton.visibility = View.GONE

                    }
                }else{

                    boton.visibility = View.GONE

                }

            }
            "146_151" -> {

                if (especies >= 1211){
                    if (pokemon.id != cajaService.findByidPokemon(pokemon.id)[0].pokemon.id){

                        boton.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(pokemon.img)))
                        boton.setBackgroundResource(R.drawable.electrico)
                        boton.setOnClickListener {

                            cajaService.add(Caja(caja[caja.size-1].id+1, pokemon.name, pokemon))
                            pokedexService.visible(pokemon.id)
                            finish()
                            startActivity(Intent(this, MainActivity::class.java))

                        }

                    }else{

                        boton.visibility = View.GONE

                    }
                }else{

                    boton.visibility = View.GONE

                }

            }
            "150_151" -> {

                if (especies >= 1346){
                    if (pokemon.id != cajaService.findByidPokemon(pokemon.id)[0].pokemon.id){

                        boton.setImageBitmap(BitmapFactory.decodeResource(resources, variablesImgPokemons.img(pokemon.img)))
                        boton.setBackgroundResource(R.drawable.electrico)
                        boton.setOnClickListener {

                            cajaService.add(Caja(caja[caja.size-1].id+1, pokemon.name, pokemon))
                            pokedexService.visible(pokemon.id)
                            finish()
                            startActivity(Intent(this, MainActivity::class.java))

                        }

                    }else{

                        boton.visibility = View.GONE

                    }
                }else{

                    boton.visibility = View.GONE

                }

            }
        }

    }

}