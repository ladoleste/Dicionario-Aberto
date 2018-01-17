package br.com.ladoleste.dicionarioaberto.dto

import com.google.gson.annotations.SerializedName

data class SuperEntry(@SerializedName("entry") val entry: Entry)