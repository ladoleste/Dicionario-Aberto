package br.com.ladoleste.dicionarioaberto.app

import br.com.ladoleste.dicionarioaberto.BuildConfig
import br.com.ladoleste.dicionarioaberto.dto.Busca
import br.com.ladoleste.dicionarioaberto.dto.Definicoes
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *Created by Anderson on 08/12/2017.
 */
interface Api {
    @GET("search-json/{palavra}")
    fun obterDefinicao(@Path("palavra") palavra: String): Single<Definicoes>

    @GET("search-json")
    fun completarPalavra(@Query("prefix") palavra: String): Observable<Busca>

    companion object {
        fun criar(): Api {

            val gson = GsonBuilder().registerTypeAdapter(Definicoes::class.java, CustomDeserializer()).create()

            val retrofit = Retrofit.Builder()
                    .client(OkHttpProvider.okHttpInstance)
                    .baseUrl(BuildConfig.API_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit.create(Api::class.java)
        }
    }
}