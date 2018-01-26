package br.com.ladoleste.dicionarioaberto.dto

import com.google.gson.annotations.SerializedName

data class Busca(
        @SerializedName("list")
        val list: List<String>
)