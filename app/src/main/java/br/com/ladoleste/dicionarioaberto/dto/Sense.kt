package br.com.ladoleste.dicionarioaberto.dto

import com.google.gson.annotations.SerializedName

data class Sense(
        @SerializedName("gramGrp")
        var gramGrp: String? = null,
        @SerializedName("def")
        var def: String? = null,
        @SerializedName("@ast")
        var ast: String? = null,
        @SerializedName("usg")
        var usg: Usg? = null
)