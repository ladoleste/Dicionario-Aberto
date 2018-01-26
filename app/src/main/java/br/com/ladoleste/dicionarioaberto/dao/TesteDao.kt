package br.com.ladoleste.dicionarioaberto.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import br.com.ladoleste.dicionarioaberto.dto.Teste

@Dao
interface TesteDao {

    @Query("SELECT id,nome FROM teste")
    fun getAll(): List<Teste>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(teste: Teste): Long
}