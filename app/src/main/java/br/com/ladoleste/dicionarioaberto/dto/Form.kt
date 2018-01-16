package br.com.ladoleste.dicionarioaberto.dto

import com.google.gson.annotations.SerializedName

data class Form(
        @SerializedName("orth")
        var orth: String? = null
)