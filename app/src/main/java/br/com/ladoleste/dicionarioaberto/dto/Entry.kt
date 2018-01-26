package br.com.ladoleste.dicionarioaberto.dto

import com.google.gson.annotations.SerializedName

data class Entry(
        @SerializedName("@id")
        var id: String? = null,
        @SerializedName("form")
        var form: Form? = null,
        @SerializedName("etym")
        var etym: Etym? = null,
        @SerializedName("@n")
        var n: String? = null,
        @SerializedName("@type")
        var type: String? = null,
        @SerializedName("@ast")
        var ast: String? = null,
        @SerializedName("sense")
        val sense: List<Sense>

)