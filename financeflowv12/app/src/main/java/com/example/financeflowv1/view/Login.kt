package com.example.financeflowv1.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.financeflowv1.routes.Routes
import com.example.financeflowv1.viewmodel.LoginViewModel

@Composable
fun Login(navController: NavHostController) {

    val context = LocalContext.current
    val loginViewModel: LoginViewModel = viewModel()

    val email by loginViewModel.email.collectAsState()
    val password by loginViewModel.senha.collectAsState()
    val error by loginViewModel.error.collectAsState()
    val success by loginViewModel.success.collectAsState()

    LaunchedEffect(error) {
        error?.let {
            Toast.makeText(context, it.ifBlank { "Erro desconhecido" }, Toast.LENGTH_LONG).show()
        }
    }

    // Redirecionamento após sucesso
    LaunchedEffect(success) {
        if (success) {
            Toast.makeText(context, "Login realizado!", Toast.LENGTH_SHORT).show()
            navController.navigate(Routes.MENU) {
                popUpTo(Routes.MENU) { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {


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

        // Layout principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título
            Text(
                text = "FAÇA LOGIN",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Campo email
            OutlinedTextField(
                value = email,
                onValueChange = { loginViewModel.setEmail(it) },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo senha
            OutlinedTextField(
                value = password,
                onValueChange = { loginViewModel.setSenha(it) },
                label = { Text("Senha") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botão Entrar
            Button(
                onClick = {
                    navController.navigate(Routes.MENU)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(text = "ENTRAR", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botão Cadastro
            Button(
                onClick = {
                    navController.navigate(Routes.CADASTRAR) {
                        popUpTo(Routes.LOGIN) { inclusive = false }  // Voltar para a tela de Login
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(text = "FAÇA SEU CADASTRO", fontSize = 16.sp)
            }



        }
    }
}