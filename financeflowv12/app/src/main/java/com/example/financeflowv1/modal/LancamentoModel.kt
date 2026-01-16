package com.example.financeflowv1.modal

// Modelagem da tabela
data class LancamentoModel(
    val tipo: String = "",
    val valor: String = "",
    val descricao: String = "",
    val data: Long = System.currentTimeMillis(),
    val usuarioId: String = ""
)
