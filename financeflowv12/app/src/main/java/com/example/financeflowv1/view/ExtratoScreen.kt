package com.example.financeflowv1.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.financeflowv1.routes.Routes
import com.example.financeflowv1.viewmodel.LancamentoViewModel

@Composable
fun Extrato(
    viewModel: LancamentoViewModel,
    navController: NavHostController
) {

    LaunchedEffect(Unit) {
        viewModel.carregarLancamentos()
    }

    Column(modifier = Modifier.fillMaxSize()) {

        // HEADER
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color(0xFFDC952B)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "EXTRATOS",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // LISTA
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {
            items(viewModel.historico) { item ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {

                    Text("Tipo: ${item.tipo}")
                    Text("Valor: R$ ${item.valor}")
                    Text("Data: ${convertMillisToDate(item.data)}")
                    Text("Descrição: ${item.descricao}")
                }
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 15.dp)
                )
            }
        }
        Button(
            onClick = { navController.navigate(Routes.MENU) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text(text = "VOLTAR AO MENU")
        }
    }
}
