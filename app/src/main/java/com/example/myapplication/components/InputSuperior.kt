package com.example.myapplication.components



import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun InputSuperior(
    valor: String,
    onValorChange: (String) -> Unit
) {
    OutlinedTextField(
        value = valor,
        onValueChange = { nuevoValor ->
            // Filtramos para que SOLO acepte números
            if (nuevoValor.all { it.isDigit() }) {
                onValorChange(nuevoValor)
            }
        },
        label = { Text("Ingrese su presupuesto") },
        placeholder = { Text("1000") },
        // Configuración para que el teclado sea numérico
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        singleLine = true
    )
}