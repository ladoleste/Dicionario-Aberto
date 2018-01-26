package br.com.ladoleste.dicionarioaberto.app

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.ladoleste.dicionarioaberto.dao.TesteDao
import br.com.ladoleste.dicionarioaberto.dto.Teste


@Database(entities = [(Teste::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun testeDao(): TesteDao
}