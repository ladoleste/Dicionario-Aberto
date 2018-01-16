package br.com.ladoleste.dicionarioaberto.dto

import com.google.gson.annotations.SerializedName

data class Usg(
        @SerializedName("@type")
        var type: String? = null,
        @SerializedName("#text")
        var text: String? = null
)