package es.di.eventos.apartado4

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import es.di.eventos.ui.*

/* ═══════════════════════════════════════════════════════════════════════
 * 4.3  PUNTERO: PULSAR Y SOLTAR  ·  Modifier.pointerInput { awaitEachGesture }
 * ═══════════════════════════════════════════════════════════════════════
 * TEORÍA (Código 1 de la teoría: la Tecla del piano)
 *
 *  clickable solo avisa cuando el clic TERMINA. Si necesitamos saber cuándo
 *  EMPIEZA (dedo abajo) y cuándo ACABA (dedo arriba) usamos pointerInput:
 *
 *    .pointerInput(clave) {
 *        awaitEachGesture {                   // bucle: se reactiva en cada gesto
 *            val down = awaitFirstDown()      // ⬇ dedo abajo  -> "pulsar"
 *            val up = waitForUpOrCancellation() // ⬆ dedo arriba -> "soltar"
 *                                             //   (null si se canceló: el dedo salió)
 *        }
 *    }
 *
 *  - El bloque es 'suspend': espera sin bloquear la interfaz.
 *  - La CLAVE de pointerInput(clave) reinicia el bloque si cambia.
 *    Sin clave (o con Unit) se queda con los valores de la PRIMERA
 *    composición: fuente típica de errores.
 * ═══════════════════════════════════════════════════════════════════════ */

@Composable
fun Ejemplo43() = Bloque(
    EJEMPLO,
    "Botón 'Mantén para grabar': mientras el dedo está abajo se pone rojo; al soltar " +
        "muestra cuántos milisegundos estuvo pulsado."
) {
    var grabando by remember { mutableStateOf(false) }
    var duracion by remember { mutableLongStateOf(0L) }

    Box(
        Modifier
            .size(140.dp)
            .background(if (grabando) Color.Red else Color.DarkGray, CircleShape)
            .pointerInput(Unit) {               // Unit: no depende de nada que cambie
                awaitEachGesture {
                    awaitFirstDown()            // ⬇ PULSAR
                    val inicio = System.currentTimeMillis()
                    grabando = true
                    waitForUpOrCancellation()   // ⬆ SOLTAR (o cancelar)
                    grabando = false
                    duracion = System.currentTimeMillis() - inicio
                }
            }
            .testTag("grabar"),
        contentAlignment = Alignment.Center
    ) { Text(if (grabando) "● REC" else "🎙️ Mantén", color = Color.White) }
    Registro("Última grabación: $duracion ms")
}

@Composable
fun Profe43() = Bloque(
    PROFE,
    "Un Box que indica 'PULSADO' / 'SUELTO' y cuenta pulsaciones. Si el dedo sale " +
        "fuera del Box antes de soltar, muestra 'CANCELADO' (waitForUpOrCancellation devuelve null)."
) {
    // TODO 1: estados 'estado' (String) y 'pulsaciones' (Int).
    // TODO 2: Box con .pointerInput(Unit) { awaitEachGesture { ... } }.
    // TODO 3: tras awaitFirstDown(): estado = "PULSADO", pulsaciones++.
    // TODO 4: val up = waitForUpOrCancellation(); estado = if (up == null) "CANCELADO" else "SUELTO".
    Pendiente()
}

@Composable
fun Alumno43() = Bloque(
    ALUMNO,
    "Zona táctil que muestra las coordenadas (x, y) donde se pulsa y donde se suelta, " +
        "y dibuja un punto en el lugar de la pulsación."
) {
    // TODO 1: estado 'texto' (String) y opcional 'abajo' (Offset?) para pintar un punto.
    // TODO 2: Box grande con .pointerInput(Unit) { awaitEachGesture { ... } }.
    // TODO 3: val down = awaitFirstDown()  -> down.position.x / .y
    // TODO 4: val up = waitForUpOrCancellation() -> up?.position
    Pendiente()
}
