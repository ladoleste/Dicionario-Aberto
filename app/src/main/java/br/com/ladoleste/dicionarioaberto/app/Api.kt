package br.com.ladoleste.dicionarioaberto.app

import br.com.ladoleste.dicionarioaberto.BuildConfig
import br.com.ladoleste.dicionarioaberto.dto.Example
import io.reactivex.Observable
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
    fun obterDefinicao(@Path("palavra") palavra: String): Observable<Example>

    @GET("search-json")
    fun obterSugestoes(@Query("prefix") prefix: String = "", @Query("suffix") suffix: String = ""): Observable<List<String>>

    companion object {
        fun criar(): Api {

            val retrofit = Retrofit.Builder()
                    .client(OkHttpProvider.okHttpInstance)
                    .baseUrl(BuildConfig.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit.create(Api::class.java)
        }
    }
}