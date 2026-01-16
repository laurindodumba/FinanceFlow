package com.example.financeflowv1.viewmodel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.example.financeflowv1.data.LancamentoEntity
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.financeflowv1.repository.LancamentoRepository



class LancamentoViewModel(
    private val repository: LancamentoRepository
) : ViewModel() {

    var tipo by mutableStateOf("CREDITO")
    var valor by mutableStateOf("")
    var descricao by mutableStateOf("")
    var data by mutableStateOf<Long?>(null)

    var historico by mutableStateOf<List<LancamentoEntity>>(emptyList())
        private set

    fun lancar(): Boolean {
        if (valor.isBlank() || descricao.isBlank() || data == null) {
            return false
        }

        viewModelScope.launch {
            repository.inserir(
                LancamentoEntity(
                    tipo = tipo,
                    valor = valor,
                    descricao = descricao,
                    data = data!!
                )
            )

            historico = repository.buscarTodos()

            valor = ""
            descricao = ""
            data = null
        }

        return true
    }


    fun carregarLancamentos() {
        viewModelScope.launch {
            historico = repository.buscarTodos()
        }
    }
}

