package com.example.sumador

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sumador.ui.theme.SumadorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SumadorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PantallaPrincipal()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PantallaPrincipal() {

    var numeroA by rememberSaveable {
        mutableStateOf("")
    }
    var numeroB by rememberSaveable {
        mutableStateOf("")
    }
    var resultado by rememberSaveable {
        mutableStateOf("0")
    }
    var isEnabled by rememberSaveable {
        mutableStateOf(false)
    }




    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.2f)
                .background(Color.Green)
        ) {

            Resultado(numero = resultado)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.4f)
                .background(androidx.compose.ui.graphics.Color.Gray)
        )
        {
            Column {
                Numero(numero = numeroA, {
                    if (esNumerica(it))
                        numeroA = it
                    if (numeroA.length > 0 && numeroB.length > 0) {
                        isEnabled = true
                    } else {
                        isEnabled = false
                    }
                })
                Numero(numero = numeroB) {
                    if (esNumerica(it))
                        numeroB = it

                    if (numeroA.length > 0 && numeroB.length > 0) {
                        isEnabled = true
                    } else {
                        isEnabled = false
                    }
                }
            }

        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(
                    androidx.compose.ui.graphics.Color.White
                )
        ) {
            Column {

                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(color = Color.Cyan)
                        .weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f), contentAlignment = Alignment.Center

                    ) {
                        OperationButton("Suma", isEnabled, {
                            resultado = (numeroA.toInt() + numeroB.toInt()).toString()
                        })
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f), contentAlignment = Alignment.Center
                    ) {
                        OperationButton("Resta", isEnabled, {
                            resultado = (numeroA.toInt() - numeroB.toInt()).toString()
                        })
                    }
                }

                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(color = Color.Cyan)
                        .weight(1f)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f), contentAlignment = Alignment.Center

                    ) {
                        OperationButton("Multiplicación", isEnabled, {
                            resultado = (numeroA.toInt() * numeroB.toInt()).toString()
                        })
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f), contentAlignment = Alignment.Center

                    ) {
                        OperationButton("División", isEnabled, {
                            resultado = (numeroA.toInt() / numeroB.toInt()).toString()
                        })
                    }
                }
            }

        }


    }
}

fun esNumerica(cadena: String): Boolean {
    return cadena.toDoubleOrNull() != null
}


@Composable
fun OperationButton(operacion: String, isEnabled: Boolean, onKeyPresseded: () -> Unit) {
    Button(
        modifier = Modifier
            .size(150.dp)
            .clip(CircleShape),
        enabled = isEnabled,
        onClick = { onKeyPresseded() }) {
        Text(text = operacion)

    }
}


@Composable
fun Numero(numero: String, onValueChanged: (String) -> Unit) {
    TextField(
        modifier = Modifier.padding(20.dp),
        value = numero,
        label = { Text(text = "Número") },
        textStyle = TextStyle(textAlign = TextAlign.End),
        onValueChange = { onValueChanged(it) })
}


@Composable
fun Resultado(numero: String) {
    Text(
        text = numero, Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 40.sp,
            shadow = Shadow(
                color = Color.Blue,
                blurRadius = 3f
            )
        ),
        textAlign = TextAlign.End,
    )
}


