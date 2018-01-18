package br.com.ladoleste.dicionarioaberto.dto

import com.google.gson.annotations.SerializedName

data class Definicoes(
        @SerializedName("superEntry")
        var superEntry: List<SuperEntry>
)