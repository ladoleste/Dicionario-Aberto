package br.com.ladoleste.dicionarioaberto.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import br.com.ladoleste.dicionarioaberto.R
import br.com.ladoleste.dicionarioaberto.app.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val rlDefinicoes by lazy {
        rl_definicoes.setHasFixedSize(true)
        rl_definicoes.layoutManager = LinearLayoutManager(this)
        rl_definicoes
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_definir.setOnClickListener {
            pb_loading.visibility = View.VISIBLE
            Api.criar().obterDefinicao(et_entrada.text.toString())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ x ->
                        pb_loading.visibility = View.GONE
                        x.entry?.let {
                            rlDefinicoes.adapter = AdapterDefinicoes(it.sense)
                            pb_loading.visibility = View.GONE
                        }

                    }, { x ->
                        pb_loading.visibility = View.GONE
                        Toast.makeText(this, x.message, Toast.LENGTH_SHORT).show()
                        Timber.e(x)
                    })
        }


    }
}