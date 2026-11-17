package es.di.eventos.apartado4

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import es.di.eventos.ui.*
import kotlin.math.roundToInt

/* ═══════════════════════════════════════════════════════════════════════
 * 4.5  ARRASTRE  ·  Modifier.pointerInput { detectDragGestures { ... } }
 * ═══════════════════════════════════════════════════════════════════════
 * TEORÍA
 *
 *  detectDragGestures es un detector ya hecho (por dentro usa awaitEachGesture)
 *  que nos da 4 eventos:
 *
 *    detectDragGestures(
 *        onDragStart  = { posicion -> },     // empieza el arrastre
 *        onDragEnd    = { },                 // se suelta
 *        onDragCancel = { },                 // se cancela
 *        onDrag       = { cambio, delta -> } // MUCHAS veces: delta = lo que se ha movido
 *    )
 *
 *  Patrón: guardamos un 'offset' en el estado, le sumamos 'delta' en onDrag
 *  y lo aplicamos con Modifier.offset { IntOffset(x, y) }.
 *
 *  Variantes: detectHorizontalDragGestures / detectVerticalDragGestures.
 *  OJO: el orden de los Modifier importa: offset ANTES de pointerInput para
 *  que la zona táctil se mueva con el componente.
 * ═══════════════════════════════════════════════════════════════════════ */

@Composable
fun Ejemplo45() = Bloque(
    EJEMPLO,
    "Arrastra el círculo por la zona gris. Se registran los eventos onDragStart, onDrag y onDragEnd."
) {
    var offset by remember { mutableStateOf(Offset.Zero) }
    var evento by remember { mutableStateOf("-") }

    Box(Modifier.fillMaxWidth().height(260.dp).background(Color(0xFFEEEEEE))) {
        Box(
            Modifier
                .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) } // 1º mover
                .size(70.dp)
                .background(Color(0xFF3F51B5), CircleShape)
                .pointerInput(Unit) {                                              // 2º escuchar
                    detectDragGestures(
                        onDragStart = { evento = "onDragStart" },
                        onDragEnd = { evento = "onDragEnd" },
                    ) { cambio, delta ->
                        cambio.consume()           // nadie más procesa este movimiento
                        offset += delta            // sumamos lo que se ha movido
                        evento = "onDrag"
                    }
                }
                .testTag("bola")
        )
    }
    Registro("$evento · x=${offset.x.roundToInt()} y=${offset.y.roundToInt()}")
}

@Composable
fun Profe45() = Bloque(
    PROFE,
    "'Desliza para desbloquear': un botón que solo se arrastra en horizontal. Si pasa de " +
        "la mitad se queda desbloqueado; si no, vuelve al inicio al soltar."
) {
    // TODO 1: estados 'x' (Float) y 'desbloqueado' (Boolean). val maximo = 600f.
    // TODO 2: Box (pista) y dentro otro Box redondo con .offset { IntOffset(x.roundToInt(), 0) }.
    // TODO 3: .pointerInput(Unit) { detectHorizontalDragGestures(onDragEnd = {...}) { _, dx -> ... } }
    // TODO 4: en onDrag: x = (x + dx).coerceIn(0f, maximo). En onDragEnd: si x > maximo/2 desbloquea, si no x = 0f.
    Pendiente()
}

@Composable
fun Alumno45() = Bloque(
    ALUMNO,
    "Cuadrado arrastrable que se vuelve naranja mientras se arrastra (onDragStart / onDragEnd) " +
        "y, al soltar, muestra 'Soltado en (x, y)'. Botón 'Centrar' para volver al origen."
) {
    // TODO 1: estados 'offset' (Offset.Zero), 'arrastrando' (Boolean) y 'mensaje' (String).
    // TODO 2: Box de 80.dp con .offset{...} y color según 'arrastrando'.
    // TODO 3: detectDragGestures con onDragStart, onDragEnd (escribe el mensaje) y onDrag (offset += delta).
    // TODO 4: TextButton "Centrar" -> offset = Offset.Zero.
    Pendiente()
}
