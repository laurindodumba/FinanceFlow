package com.example.financeflowv1.view.components
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.financeflowv1.data.LancamentoEntity
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ExtratoItem(lancamento: LancamentoEntity) {

    val cor = if (lancamento.tipo == "DEBITO") Color.Red else Color(0xFF2E7D32)
    val dataFormatada = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        .format(Date(lancamento.data))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = lancamento.descricao)
            Text(text = dataFormatada)
        }

        Text(
            text = "R$ %.2f".format(lancamento.valor),
            color = cor
        )
    }
}
