package br.com.ladoleste.dicionarioaberto.dto

import com.google.gson.annotations.SerializedName

data class Entry(

        @SerializedName("@id")
        var id: String? = null,
        @SerializedName("form")
        var form: Form? = null,
        @SerializedName("sense")
        var sense: List<Sense>? = null,
        @SerializedName("etym")
        var etym: Etym? = null
)