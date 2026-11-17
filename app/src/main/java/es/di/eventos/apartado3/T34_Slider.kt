package es.di.eventos.apartado3

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.sp
import es.di.eventos.ui.*
import kotlin.math.roundToInt

/* ═══════════════════════════════════════════════════════════════════════
 * 3.4  SLIDER  ·  eventos: onValueChange  +  onValueChangeFinished
 * ═══════════════════════════════════════════════════════════════════════
 * TEORÍA
 *
 *  Un Slider genera DOS eventos distintos:
 *    - onValueChange(v)        -> MUCHAS veces, durante el arrastre.
 *                                 Sirve para PREVISUALIZAR.
 *    - onValueChangeFinished() -> UNA vez, al soltar el dedo.
 *                                 Sirve para CONFIRMAR (guardar, enviar...).
 *
 *  Acción típica (tabla 3.1): previsualizar durante el arrastre y confirmar
 *  al soltar. Así no guardamos 100 valores intermedios.
 * ═══════════════════════════════════════════════════════════════════════ */

@Composable
fun Ejemplo34() = Bloque(
    EJEMPLO,
    "Slider de volumen: el número cambia mientras arrastras; el valor 'confirmado' " +
        "solo cambia al soltar. Se cuentan los eventos de cada tipo."
) {
    var vistaPrevia by remember { mutableFloatStateOf(50f) }
    var confirmado by remember { mutableFloatStateOf(50f) }
    var nCambios by remember { mutableIntStateOf(0) }
    var nFinales by remember { mutableIntStateOf(0) }

    Text("Previsualizando: ${vistaPrevia.roundToInt()}")
    Slider(
        value = vistaPrevia,
        onValueChange = { vistaPrevia = it; nCambios++ },             // durante el arrastre
        onValueChangeFinished = { confirmado = vistaPrevia; nFinales++ }, // al soltar
        valueRange = 0f..100f,
        modifier = Modifier.testTag("slider")
    )
    Text("Confirmado: ${confirmado.roundToInt()}")
    Registro("onValueChange x$nCambios · onValueChangeFinished x$nFinales")
}

@Composable
fun Profe34() = Bloque(
    PROFE,
    "Slider que cambia el tamaño de letra de un texto (12..40 sp) en directo. " +
        "Al soltar muestra 'Tamaño guardado: N sp'."
) {
    // TODO 1: estado 'tamano' (Float, 20f) y 'mensaje' (String).
    // TODO 2: Text("Aa Bb Cc", fontSize = tamano.sp)
    // TODO 3: Slider con valueRange = 12f..40f; onValueChange actualiza el tamaño.
    // TODO 4: onValueChangeFinished escribe el mensaje "Tamaño guardado".
    Pendiente()
}

@Composable
fun Alumno34() = Bloque(
    ALUMNO,
    "Valoración con Slider de 0 a 5 a saltos enteros (steps = 4). Mientras arrastras se " +
        "ven las estrellas ★★★☆☆; al soltar se suma un voto y se muestra la media."
) {
    // TODO 1: estado 'estrellas' (Float) y 'votos' (List<Int>).
    // TODO 2: pinta "★".repeat(n) + "☆".repeat(5 - n), con n = estrellas.roundToInt().
    // TODO 3: Slider 0f..5f con steps = 4.
    // TODO 4: en onValueChangeFinished añade el voto: votos = votos + n. Muestra la media.
    Pendiente()
}
