package com.example.financeflowv1.repository
import com.example.financeflowv1.data.LancamentoDao
import com.example.financeflowv1.data.LancamentoEntity


class LancamentoRepository(
    private val dao: LancamentoDao
) {

    suspend fun inserir(lancamento: LancamentoEntity) {
        dao.inserir(lancamento)
    }

    suspend fun buscarTodos(): List<LancamentoEntity> {
        return dao.listarLancamentos()
    }
}

