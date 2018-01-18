package br.com.ladoleste.dicionarioaberto.dto

import com.google.gson.annotations.SerializedName

data class Definicao(
        @SerializedName("entry")
        var entry: Entry
)