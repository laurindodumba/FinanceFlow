package com.example.financeflowv1.view

import android.widget.Space
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.financeflowv1.routes.Routes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun Cadastrar(navController: NavHostController) {

    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

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
            .fillMaxSize()
            //.background(Color.Yellow)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "CADASTRAR USUÁRIO",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (nome.isBlank() || email.isBlank() || senha.isBlank()) {
                    Toast.makeText(
                        context,
                        "Preencha todos os campos",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@Button
                }

                auth.createUserWithEmailAndPassword(email, senha)
                    .addOnSuccessListener { result ->

                        val uid = result.user!!.uid

                        val userData = hashMapOf(
                            "nome" to nome,
                            "email" to email,
                            "uid" to uid
                        )

                        db.collection("usuarios")
                            .document(uid)
                            .set(userData)
                            .addOnSuccessListener {
                                Toast.makeText(
                                    context,
                                    "Cadastro realizado com sucesso!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                navController.popBackStack()
                            }
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            context,
                            "Erro: ${it.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(14.dp)
        ) {
            Text("CRIAR CONTA", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botão para voltar ao Login

        Button(
            onClick = {
                navController.navigate(Routes.LOGIN)
            },

                    modifier = Modifier
                    .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(14.dp)

        ) {
            Text(text = "VOLTAR TELA DE LOGIN", fontSize = 16.sp)
        }


    }
}

