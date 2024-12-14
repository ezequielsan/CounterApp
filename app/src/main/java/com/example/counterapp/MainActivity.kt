package com.example.counterapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.counterapp.ui.theme.CounterAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CounterAppTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    CounterApp()
                }
            }
        }
    }
}


// Função composable principal que define a interface do usuário da calculadora
@Composable
fun CounterApp() {

    // Estado para armazenar o resultado das operações
    var result by remember { mutableStateOf(0.0) }

    // Estados para armazenar o valor de entrada do usuário
    var inputOne by remember { mutableStateOf("") }
    var inputTwo by remember { mutableStateOf("") }

    // Estrutura uma coluna para alinhar os elementos verticalmente
    Column (
        horizontalAlignment = Alignment.CenterHorizontally, // Alinha horizontalmente ao centro
        modifier = Modifier
            .fillMaxSize()  // Ocupa todo o espaço disponivel
            .padding(16.dp),    // Adiciona espaçamento nas bordas
        verticalArrangement = Arrangement.Center    // Centraliza os elementos verticalmente
    ){
        // Exibe o resultado atual
        Text(
            text = "Resultado: $result",
            fontSize = 24.sp,
            modifier = Modifier.padding(8.dp)
        )

        Row (
            horizontalArrangement = Arrangement.spacedBy(16.dp),  // Espaçamento entre os botões
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = inputOne,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) inputOne = newValue
                }, // Atualiza o valor de input com o valor digitado
                label = { Text("Digite um número") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.weight(1f)
            )

            OutlinedTextField(
                value = inputTwo,
                onValueChange = { newValue ->
                    if (newValue.all { it.isDigit() }) inputTwo = newValue
                }, // Atualiza o valor de input com o valor digitado
                label = { Text("Digite outro número") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.weight(1f)

            )
        }


        Spacer( modifier = Modifier.height(16.dp) ) // Espaço entre o campo de entrada e os botões

        Row (
            horizontalArrangement = Arrangement.spacedBy(8.dp),  // Espaçamento entre os botões
            modifier = Modifier.fillMaxWidth()
        ){
            // Botão incrementar
            Button(
                onClick = {
                    result = (inputOne.toDoubleOrNull() ?: 0.0) + (inputTwo.toDoubleOrNull() ?: 0.0)
                    inputOne = ""
                    inputTwo = ""
                },
                modifier = Modifier.weight(1f)  // O botão ocupa metade da linha
            ) {
                Text("Soma")
            }

            // Botão decrementar
            Button(
                onClick = {
                    result = (inputOne.toDoubleOrNull() ?: 0.0) - (inputTwo.toDoubleOrNull() ?: 0.0)
                    inputOne = ""
                    inputTwo = ""
                },
                modifier = Modifier.weight(1f)  // O botão ocupa metade da linha
            ) {
                Text("Subtrair")
            }
        }

        Row (
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)    // Espaçamento superior
        ) {
            // Botão de multiplicar
            Button(
                onClick = {
                    result = (inputOne.toDoubleOrNull() ?: 0.0) * (inputTwo.toDoubleOrNull() ?: 0.0)
                    inputOne = ""
                    inputTwo = ""
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Multiplicar")
            }

            // Botão de devidir
            Button(
                onClick = {
                    val value = inputTwo.toDoubleOrNull() ?: 0.0
                    if (value != 0.0) { result = (inputOne.toDoubleOrNull()?: 0.0) / value }
                    inputOne = ""
                    inputTwo = ""
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Dividir")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))   // Espaço antes do botão de limpar

        Button(
            onClick = {
                result = 0.0
                inputOne = ""
                inputTwo = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Limpar")
        }

    }

}

