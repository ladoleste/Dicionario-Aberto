package br.com.ladoleste.dicionarioaberto.ui

import android.arch.lifecycle.ViewModel
import br.com.ladoleste.dicionarioaberto.app.Api
import br.com.ladoleste.dicionarioaberto.dto.Busca
import br.com.ladoleste.dicionarioaberto.dto.Definicoes
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *Created by Anderson on 10/12/2017.
 */
class MainViewModel : ViewModel() {

    fun obterDefinicao(palavra: String): Observable<Definicoes> {
        return Api.criar().obterDefinicao(palavra)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun buscarPalavra(termo: String): Observable<Busca> {
        return Api.criar().buscarPalavra(termo)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }
}