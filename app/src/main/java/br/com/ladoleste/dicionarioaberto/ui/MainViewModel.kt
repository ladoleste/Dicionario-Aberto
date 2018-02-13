package br.com.ladoleste.dicionarioaberto.ui

import android.arch.lifecycle.ViewModel
import br.com.ladoleste.dicionarioaberto.app.Api
import br.com.ladoleste.dicionarioaberto.dto.Busca
import br.com.ladoleste.dicionarioaberto.dto.Definicoes
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 *Created by Anderson on 10/12/2017.
 */
class MainViewModel : ViewModel() {

    private val api = Api.criar()

    fun obterDefinicao(palavra: String): Single<Definicoes> {

        return api.obterDefinicao(palavra)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError({ t -> Timber.e(t) })
    }

    fun completarPalavra(termo: String): Observable<Busca> {
        return api.completarPalavra(termo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError({ t -> Timber.e(t) })
    }
}