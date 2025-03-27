package com.example.cacaaotesouro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()

    NavHost(navController = navigationController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onNavigateToTela02 = { navigationController.navigate("tela02") }
            )
        }
        composable("tela02") {
            Tela02Screen(
                onNavigateToHome = { navigationController.navigate("home") },
                onNavigateToTela03 = { navigationController.navigate("tela03") }
            )
        }
        composable("tela03") {
            Tela03Screen(
                onNavigateToTela02 = { navigationController.navigate("tela02") },
                onNavigateToHome = { navigationController.navigate("home") }
            )
        }
    }
}

@Composable
fun HomeScreen(onNavigateToTela02: () -> Unit,) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Caça ao tesouro", fontSize = 43.sp)
        Button(onClick = {onNavigateToTela02()
                         println("Ta funfando")
        }, modifier = Modifier.padding(20.dp)
            .width(300.dp)
            .height(100.dp)
        ) { Text("Começar o desafio", fontSize = 27.sp) }
    }
}


@Composable

fun Tela02Screen(onNavigateToHome: () -> Unit, onNavigateToTela03: () -> Unit) {
    var respostaQSt1 = remember { mutableStateOf(TextFieldValue()) }

    Button(
        onClick = {onNavigateToHome()}
    ) { Text(text = "Voltar") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Desafio 1: ", fontSize = 24.sp)
        Text(text = "O que pode encher uma sala, mas não ocupa espaço?", fontSize = 16.sp)


        TextField(
            value = respostaQSt1.value,
            onValueChange = { newText -> respostaQSt1.value = newText },
            label = { Text("Resposta") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Button(
            onClick = {
                if (respostaQSt1.value.text.lowercase() == "luz") {
                    onNavigateToTela03()
                } else {
                    println("errou")
                }
            }
        ) {
            Text("Responder")
        }
    }
}


@Composable
fun Tela03Screen(onNavigateToTela02: () -> Unit, onNavigateToHome: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Desafio 2:", fontSize = 24.sp)
        Button(onClick = onNavigateToTela02) { Text("Ir para Tela 02") }
        Button(onClick = onNavigateToHome) { Text("Voltar para Home") }
    }
}

@Preview(showBackground = true)
@Composable
fun Teste() {
    Tela03Screen(onNavigateToTela02 = {}, onNavigateToHome = {})
}
