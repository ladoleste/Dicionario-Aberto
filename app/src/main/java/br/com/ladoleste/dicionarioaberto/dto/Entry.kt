package br.com.ladoleste.dicionarioaberto.dto

import com.google.gson.annotations.SerializedName

data class Entry(

        @SerializedName("@id")
        var id: String? = null,
        @SerializedName("form")
        var form: Form? = null,
        @SerializedName("sense")
        val sense: List<Sense>,
        @SerializedName("etym")
        var etym: Etym? = null
)