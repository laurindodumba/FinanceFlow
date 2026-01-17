package com.example.financeflowv1.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.financeflowv1.viewmodel.LancamentoViewModel
import java.text.SimpleDateFormat
import java.util.*

/* =======================
   MODEL
   ======================= */
data class LancamentoData(
    val tipo: String,
    val valor: String,
    val descricao: String,
    val data: Long
)

/* =======================
   VIEWMODEL
   ======================= */
class LancamentoViewModel {

    var tipo by mutableStateOf("CREDITO")
    var valor by mutableStateOf("")
    var descricao by mutableStateOf("")
    var data by mutableStateOf<Long?>(null)

    private val _historico = mutableStateListOf<LancamentoData>()
    val historico: List<LancamentoData> get() = _historico

    fun lancar(): Boolean {
        if (valor.isBlank() || descricao.isBlank() || data == null) {
            return false
        }

        _historico.add(
            LancamentoData(
                tipo = tipo,
                valor = valor,
                descricao = descricao,
                data = data!!
            )
        )

        // limpa campos
        valor = ""
        descricao = ""
        data = null

        return true
    }
}

/* =======================
   TELA DE LANÇAMENTO
   ======================= */
@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun LancamentoScreen(
    viewModel: LancamentoViewModel,
    navController: NavHostController
) {
    var showDatePicker by remember { mutableStateOf(false) }
    var showErro by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {

        // HEADER
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xFFDC952B)),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "FINANCE FLOW",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(text = "TELA DE LANÇAMENTO", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(16.dp))

            LancamentoForm(
                tipo = viewModel.tipo,
                onTipoChange = { viewModel.tipo = it },
                valor = viewModel.valor,
                onValorChange = { viewModel.valor = it },
                descricao = viewModel.descricao,
                onDescricaoChange = { viewModel.descricao = it },
                data = viewModel.data,
                onDataChange = { viewModel.data = it },
                onClickDate = { showDatePicker = true }
            )

            Spacer(modifier = Modifier.height(16.dp))

            LancamentoButtons(
                onLancar = {
                    val sucesso = viewModel.lancar()
                    if (sucesso) {
                        navController.popBackStack()
                    } else {
                        showErro = true
                    }
                },
                onVerHistorico = {
                    navController.navigate("historico")
                }
            )

            if (showErro) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Preencha todos os campos antes de lançar",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }

    if (showDatePicker) {
        DatePickerModal(
            onDateSelected = {
                viewModel.data = it
                showDatePicker = false
            },
            onDismiss = { showDatePicker = false }
        )
    }
}


/* =======================
   FORMULÁRIO
   ======================= */
@Composable
fun LancamentoForm(
    tipo: String,
    onTipoChange: (String) -> Unit,
    valor: String,
    onValorChange: (String) -> Unit,
    descricao: String,
    onDescricaoChange: (String) -> Unit,
    data: Long?,
    onDataChange: (Long) -> Unit,
    onClickDate: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(

                selected = tipo == "CREDITO",
                onClick = { onTipoChange("CREDITO") }

            )
            Text("Crédito")

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = tipo == "DEBITO",
                onClick = { onTipoChange("DEBITO") }
            )
            Text("Débito")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = valor,
            onValueChange = onValorChange,
            label = { Text("Digite o valor R$") },
            placeholder = { Text("0,00") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = descricao,
            onValueChange = onDescricaoChange,
            label = { Text("Descrição") },
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClickDate() }
        ) {
            OutlinedTextField(
                value = data?.let { convertMillisToDate(it) } ?: "",
                onValueChange = {},
                enabled = false,
                label = { Text("Data") },
                placeholder = { Text("DD/MM/YYYY") },
                trailingIcon = {
                    Icon(
                        Icons.Default.DateRange,
                        contentDescription = "Selecionar data"
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )
        }

    }
}


// BOTÕES


@Composable
fun LancamentoButtons(
    onLancar: () -> Unit,
    onVerHistorico: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Button(
            onClick = onLancar,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("LANÇAR")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onVerHistorico,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text("VER LANÇAMENTOS", fontSize = 16.sp)
        }
    }
}


// DATE PICKER

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                datePickerState.selectedDateMillis?.let { onDateSelected(it) }
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}


// UTIL

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}
