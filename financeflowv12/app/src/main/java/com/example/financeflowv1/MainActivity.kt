package com.example.financeflowv1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.financeflowv1.database.AppDatabase
import com.example.financeflowv1.repository.LancamentoRepository
import com.example.financeflowv1.routes.Routes
import com.example.financeflowv1.ui.theme.Financeflowv1Theme
import com.example.financeflowv1.view.*
import com.example.financeflowv1.viewmodel.LancamentoViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Financeflowv1Theme {

                val navController = rememberNavController()

                // ✅ CRIA BANCO, REPOSITORY E VIEWMODEL UMA ÚNICA VEZ
                val database = remember {
                    Room.databaseBuilder(
                        applicationContext,
                        AppDatabase::class.java,
                        "finance_db"
                    ).build()
                }

                val repository = remember {
                    LancamentoRepository(database.lancamentoDao())
                }

                val lancamentoViewModel = remember {
                    LancamentoViewModel(repository)
                }

                NavHost(
                    navController = navController,
                    startDestination = Routes.SPLASH
                ) {

                    composable(Routes.SPLASH) {
                        SplashScreen(navController)
                    }

                    composable(Routes.LOGIN) {
                        Login(navController)
                    }

                    composable(Routes.CADASTRAR) {
                        Cadastrar(navController)
                    }

                    composable(Routes.MENU) {
                        Menu(navController)
                    }

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
        }
    }
}
