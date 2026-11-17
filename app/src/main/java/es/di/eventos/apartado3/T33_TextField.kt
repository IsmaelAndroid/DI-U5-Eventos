package es.di.eventos.apartado3

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import es.di.eventos.ui.*

/* ═══════════════════════════════════════════════════════════════════════
 * 3.3  OUTLINEDTEXTFIELD  ·  eventos: onValueChange  +  acción de teclado
 * ═══════════════════════════════════════════════════════════════════════
 * TEORÍA
 *
 *  - onValueChange(nuevoTexto): se lanza con CADA tecla. El campo NO escribe
 *    nada por sí solo: nos propone el texto nuevo y NOSOTROS decidimos si
 *    lo aceptamos (lo guardamos en el estado) o lo rechazamos (no hacemos nada).
 *    Acción típica (tabla 3.1): VALIDAR y escribir solo si es válido.
 *
 *  - Acción de teclado (onKeyboardAction en la tabla): se lanza al pulsar el
 *    botón de acción del teclado (Hecho, Siguiente, Buscar...).
 *    Se configura con:
 *        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
 *        keyboardActions = KeyboardActions(onDone = { ... })
 * ═══════════════════════════════════════════════════════════════════════ */

@Composable
fun Ejemplo33() = Bloque(
    EJEMPLO,
    "Campo 'Edad': solo acepta dígitos (máx. 3). Al pulsar 'Hecho' en el teclado se guarda."
) {
    var edad by remember { mutableStateOf("") }
    var guardada by remember { mutableStateOf("nada todavía") }

    OutlinedTextField(
        value = edad,                                      // el estado ENTRA
        onValueChange = { nuevo ->                         // el evento SALE
            // VALIDAR: solo escribimos si el texto propuesto es válido.
            if (nuevo.length <= 3 && nuevo.all { it.isDigit() }) edad = nuevo
            // Si no es válido no hacemos nada -> el campo no cambia.
        },
        label = { Text("Edad") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { guardada = edad }),  // acción de teclado
        modifier = Modifier.testTag("campo")
    )
    Registro("Edad guardada: $guardada")
}

@Composable
fun Profe33() = Bloque(
    PROFE,
    "Campo 'Nombre' de máximo 10 letras con contador '3/10' debajo (supportingText). " +
        "Al pulsar 'Hecho' muestra 'Hola, <nombre>'."
) {
    // TODO 1: estados 'nombre' y 'saludo' (String vacíos).
    // TODO 2: OutlinedTextField: en onValueChange acepta el texto solo si length <= 10.
    // TODO 3: supportingText = { Text("${nombre.length}/10") }.
    // TODO 4: imeAction = ImeAction.Done y en onDone: saludo = "Hola, $nombre".
    Pendiente()
}

@Composable
fun Alumno33() = Bloque(
    ALUMNO,
    "Campo 'Email': escribe siempre, pero marca el campo en rojo (isError) si no contiene '@'. " +
        "Al pulsar 'Enviar' (ImeAction.Send) muestra 'Enviado a <email>' o 'Email no válido'."
) {
    // TODO 1: estados 'email' y 'resultado'. Calcula val valido = "@" in email.
    // TODO 2: onValueChange siempre guarda el texto.
    // TODO 3: isError = email.isNotEmpty() && !valido
    // TODO 4: imeAction = ImeAction.Send y KeyboardActions(onSend = { ... }).
    Pendiente()
}
