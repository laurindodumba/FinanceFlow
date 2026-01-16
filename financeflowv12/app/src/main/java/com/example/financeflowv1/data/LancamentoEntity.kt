package com.example.financeflowv1.data
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lancamentos")
data class LancamentoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tipo: String,
    val valor: String,
    val descricao: String,
    val data: Long
)
