package com.example.financeflowv1.data
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


// Buscar os dados
@Dao
interface LancamentoDao {

    @Insert
    suspend fun inserir(lancamento: LancamentoEntity)
    @Query("SELECT * FROM lancamentos ORDER BY data DESC")
    suspend fun listarLancamentos(): List<LancamentoEntity>

}

