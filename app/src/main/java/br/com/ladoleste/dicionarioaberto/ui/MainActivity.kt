package br.com.ladoleste.dicionarioaberto.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.ladoleste.dicionarioaberto.R
import br.com.ladoleste.dicionarioaberto.app.Api
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_definir.setOnClickListener {
            Api.criar().obterDefinicao(et_entrada.text.toString())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ x ->
                        x.entry?.sense?.forEach { Timber.d(it.toString()) }
                    }, { x ->
                        Timber.e(x)
                    })
        }
    }
}