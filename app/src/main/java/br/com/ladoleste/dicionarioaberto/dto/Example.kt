package br.com.ladoleste.dicionarioaberto.dto

import com.google.gson.annotations.SerializedName

data class Example(
        @SerializedName("entry")
        var entry: Entry? = null
)