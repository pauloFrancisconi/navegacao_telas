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
import androidx.navigation.NavController
import androidx.navigation.compose.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

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
                onNavigateToHome = { navigationController.navigate("home") },
                onNavigateToTela04 = { navigationController.navigate("tela04") }
            )
        }

        composable("tela04"){
            Tela04Screen(
                onNavigateToHome = { navigationController.navigate("home") },
                onNavigateToTelaParabens = { navigationController.navigate("tela05") }

            )
        }

        composable("tela05") {
            Tela05Screen(navController = navigationController)
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

fun Tela02Screen(onNavigateToHome: () -> Unit, onNavigateToTela03: () -> Unit,) {
    var respostaQSt1 = remember { mutableStateOf(TextFieldValue()) }


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
fun Tela03Screen(onNavigateToTela04: () -> Unit, onNavigateToHome: () -> Unit) {


    var respostaQSt2 = remember { mutableStateOf(TextFieldValue()) }

    Button(
        modifier = Modifier.padding(20.dp),
        onClick = onNavigateToHome
    ) { Text(text = "Voltar") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Desafio 2: ", fontSize = 24.sp)
        Text(text = "O que é leve como uma pena, mas nem a pessoa mais forte consegue segurar por muito tempo?", fontSize = 16.sp)


        TextField(
            value = respostaQSt2.value,
            onValueChange = { newText -> respostaQSt2.value = newText },
            label = { Text("Resposta") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Button(
            onClick = {
                if (respostaQSt2.value.text.lowercase() == "respiracao") {
                    onNavigateToTela04()
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
fun Tela04Screen(onNavigateToTelaParabens: () -> Unit, onNavigateToHome: () -> Unit) {
    var respostaQSt3 = remember { mutableStateOf(TextFieldValue()) }

    Button(
        modifier = Modifier.padding(20.dp),
        onClick = onNavigateToHome
    ) { Text(text = "Voltar") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Desafio 3: ", fontSize = 24.sp)
        Text(text = "O que é que sobe, mas nunca desce?", fontSize = 16.sp)


        TextField(
            value = respostaQSt3.value,
            onValueChange = { newText -> respostaQSt3.value = newText },
            label = { Text("Resposta") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Button(
            onClick = {
                if (respostaQSt3.value.text.lowercase() == "idade") {
                    onNavigateToTelaParabens()
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
fun Tela05Screen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Parabéns, Você encontrou o tesouro!", fontSize = 24.sp)


        Image(
            painter = painterResource(id = R.drawable.parabens),
            contentDescription = "Imagem de Parabéns",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))


        Button(onClick = {
            navController.navigate("home") {
                popUpTo("home") { inclusive = true }
            }
        }) {
            Text("Voltar a home")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Teste() {
    Tela03Screen(onNavigateToTela04 = {}, onNavigateToHome = {})
}
