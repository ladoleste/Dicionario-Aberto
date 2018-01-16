package br.com.ladoleste.dicionarioaberto.dto

import com.google.gson.annotations.SerializedName

data class Etym(
        @SerializedName("@orig")
        var orig: String? = null,
        @SerializedName("#text")
        var text: String? = null
)