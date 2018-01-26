package br.com.ladoleste.dicionarioaberto.dto

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Teste(
        @PrimaryKey
        val id: Int,
        val nome: String
)