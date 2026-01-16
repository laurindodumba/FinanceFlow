package com.example.financeflowv1.viewmodel
import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.financeflowv1.data.LancamentoEntity
import com.example.financeflowv1.database.AppDatabase
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue



// Extratos

class ExtratoViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase
        .getDatabase(application)
        .lancamentoDao()

    var lancamentos by mutableStateOf<List<LancamentoEntity>>(emptyList())
        private set

    fun carregarLancamentos() {
        viewModelScope.launch {
            lancamentos = dao.listarLancamentos()
        }
    }
}
