package br.com.ladoleste.dicionarioaberto.app

import br.com.ladoleste.dicionarioaberto.dto.Definicao
import br.com.ladoleste.dicionarioaberto.dto.Definicoes
import br.com.ladoleste.dicionarioaberto.dto.SuperEntry
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CustomDeserializer : JsonDeserializer<Definicoes> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Definicoes {

        return if (json.toString().startsWith("{\"superEntry")) {
            Gson().fromJson(json, Definicoes::class.java)
        } else {
            val example = Gson().fromJson(json, Definicao::class.java)
            Definicoes(listOf(SuperEntry(example.entry)))
        }
    }
}