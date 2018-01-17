package br.com.ladoleste.dicionarioaberto.app

import br.com.ladoleste.dicionarioaberto.dto.Example
import br.com.ladoleste.dicionarioaberto.dto.SuperEntry
import br.com.ladoleste.dicionarioaberto.dto.SuperExample
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CustomDeserializer : JsonDeserializer<SuperExample> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): SuperExample {

        return if (json.toString().contains("superEntry")) {
            Gson().fromJson(json, SuperExample::class.java)
        } else {
            val example = Gson().fromJson(json, Example::class.java)
            SuperExample(listOf(SuperEntry(example.entry)))
        }
    }
}