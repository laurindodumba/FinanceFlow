package com.example.financeflowv1.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.financeflowv1.routes.Routes
import com.example.financeflowv1.view.Extrato
import com.example.financeflowv1.view.LancamentoScreen
import com.example.financeflowv1.viewmodel.LancamentoViewModel


@Composable
fun NavGraph(
    navController: NavHostController,
    lancamentoViewModel: LancamentoViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.MENU
    ) {

        composable(Routes.EXTRATO) {
            Extrato(
                viewModel = lancamentoViewModel,
                navController = navController
            )
        }

        composable(Routes.LANCAMENTO) {
            LancamentoScreen(
                viewModel = lancamentoViewModel,
                navController = navController
            )
        }
    }
}

