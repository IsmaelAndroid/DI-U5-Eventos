package es.di.eventos.apartado4

import android.view.KeyEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import es.di.eventos.ui.*

/* ═══════════════════════════════════════════════════════════════════════
 * 4.2  EVENTOS DE TECLADO  ·  onKeyEvent / onPreviewKeyEvent
 * ═══════════════════════════════════════════════════════════════════════
 * TEORÍA
 *
 *  - Solo recibe teclas el componente que tiene el FOCO. Por eso añadimos
 *    Modifier.focusable() y pedimos el foco con un FocusRequester.
 *  - Los eventos de tecla viajan por el árbol en DOS fases:
 *       1) onPreviewKeyEvent : de PADRE a HIJO (antes de que llegue al hijo).
 *       2) onKeyEvent        : de HIJO a PADRE (sube después).
 *  - Ambos devuelven Boolean:
 *       true  -> "lo consumo": el evento se para aquí.
 *       false -> "no es mío": el evento sigue su camino.
 *  - Cada pulsación genera DOS eventos: KeyDown y KeyUp (evento.type).
 *    Filtra por uno de ellos o reaccionarás dos veces.
 *  - OJO: el teclado en pantalla (IME) normalmente NO envía KeyEvents.
 *    Pruébalo con teclado físico / teclado del PC en el emulador.
 * ═══════════════════════════════════════════════════════════════════════ */

@Composable
fun Ejemplo42() = Bloque(
    EJEMPLO,
    "Pulsa 'Dar foco' y mueve la bola con las flechas ← →. El Box consume las " +
        "flechas (true) y deja pasar el resto (false)."
) {
    var posicion by remember { mutableIntStateOf(5) }
    var ultima by remember { mutableStateOf("-") }
    var tieneFoco by remember { mutableStateOf(false) }
    val foco = remember { FocusRequester() }

    Box(
        Modifier
            .fillMaxWidth()
            .border(3.dp, if (tieneFoco) Color.Blue else Color.LightGray)
            .focusRequester(foco)                       // para poder pedir el foco
            .onFocusChanged { tieneFoco = it.isFocused }
            .onKeyEvent { evento ->                     // ← evento de teclado
                if (evento.type != KeyEventType.KeyDown) return@onKeyEvent false
                ultima = KeyEvent.keyCodeToString(evento.nativeKeyEvent.keyCode)  // p.ej. KEYCODE_DPAD_RIGHT
                when (evento.key) {
                    Key.DirectionLeft -> { posicion = (posicion - 1).coerceAtLeast(0); true }
                    Key.DirectionRight -> { posicion = (posicion + 1).coerceAtMost(10); true }
                    else -> false                       // no la consumimos
                }
            }
            .focusable()                                // sin esto no recibe teclas
            .padding(16.dp)
            .testTag("pista")
    ) {
        Text("-".repeat(posicion) + "●" + "-".repeat(10 - posicion), fontFamily = FontFamily.Monospace,
            style = MaterialTheme.typography.headlineSmall)
    }
    Button(onClick = { foco.requestFocus() }, Modifier.padding(top = 8.dp).testTag("darFoco")) { Text("Dar foco") }
    Registro("foco=$tieneFoco · última tecla: $ultima")
}

@Composable
fun Profe42() = Bloque(
    PROFE,
    "Campo de texto que NO deja escribir números. Usa onPreviewKeyEvent: intercepta la " +
        "tecla ANTES de que llegue al TextField y la consume si es un dígito."
) {
    // TODO 1: estados 'texto' y 'bloqueadas'.
    // TODO 2: OutlinedTextField normal con value/onValueChange.
    // TODO 3: en su modifier, .onPreviewKeyEvent { e -> ... } que devuelva true si
    //         e.utf16CodePoint.toChar().isDigit() (y cuente las bloqueadas en KeyDown).
    Pendiente()
}

@Composable
fun Alumno42() = Bloque(
    ALUMNO,
    "Un Box con foco que cambia de color con el teclado: R = rojo, G = verde, " +
        "B = azul y Escape = gris. Solo reacciona en KeyUp."
) {
    // TODO 1: estado 'color' (Color.Gray) y un FocusRequester.
    // TODO 2: Box(size 150.dp) con .background(color).focusRequester(foco).onKeyEvent{...}.focusable()
    // TODO 3: en onKeyEvent: ignora lo que no sea KeyUp; when(e.key) Key.R / Key.G / Key.B / Key.Escape.
    //         Devuelve true solo si has usado la tecla.
    // TODO 4: Button "Dar foco" -> foco.requestFocus().
    Pendiente()
}
