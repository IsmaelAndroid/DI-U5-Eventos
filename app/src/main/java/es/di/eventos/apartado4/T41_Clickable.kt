package es.di.eventos.apartado4

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import es.di.eventos.ui.*

/* ═══════════════════════════════════════════════════════════════════════
 * 4.1  EVENTO DE ACCIÓN (pulsar)  ·  onClick / Modifier.clickable
 * ═══════════════════════════════════════════════════════════════════════
 * TEORÍA (apartado 4 · Tipos de evento y su Modifier)
 *
 *  En Compose cada TIPO de evento se captura con un Modifier concreto:
 *
 *    Acción (pulsar)       -> onClick de Button  o  Modifier.clickable
 *    Teclado               -> Modifier.onKeyEvent / onPreviewKeyEvent   (4.2)
 *    Puntero pulsar/soltar -> Modifier.pointerInput { awaitEachGesture } (4.3)
 *    Puntero entrar/salir  -> Modifier.hoverable                         (4.4)
 *    Arrastre              -> pointerInput { detectDragGestures }        (4.5)
 *    Foco                  -> Modifier.onFocusEvent + FocusRequester     (4.6)
 *    Cambio de valor       -> onValueChange / onCheckedChange            (4.7)
 *
 *  Modifier.clickable convierte CUALQUIER composable (Box, Text, Card...)
 *  en pulsable: añade el efecto ripple, la semántica de "botón" para
 *  accesibilidad y responde a teclado (Enter) si tiene foco.
 *
 *  Modifier.combinedClickable añade onLongClick y onDoubleClick.
 * ═══════════════════════════════════════════════════════════════════════ */

@Composable
fun Ejemplo41() = Bloque(
    EJEMPLO,
    "Lista de frutas: cada fila es un Text con Modifier.clickable. La pulsada se resalta."
) {
    var elegida by remember { mutableStateOf("") }

    listOf("🍎 Manzana", "🍌 Plátano", "🍇 Uvas").forEach { fruta ->
        Text(
            fruta,
            Modifier
                .fillMaxWidth()
                .background(if (fruta == elegida) Color(0xFFBBDEFB) else Color.Transparent)
                .clickable { elegida = fruta }        // ← evento de acción sobre un Text
                .padding(16.dp)
                .testTag("fila-$fruta")
        )
    }
    Registro("clickable -> ${elegida.ifEmpty { "nada" }}")
}

@Composable
fun Profe41() = Bloque(
    PROFE,
    "Un cuadrado (Box) clickable que cambia de color en cada pulsación " +
        "(rojo → verde → azul → rojo...) y cuenta los clics."
) {
    // TODO 1: lista de 3 colores y estado 'clics'.
    // TODO 2: Box(Modifier.size(140.dp).background(colores[clics % 3]).clickable { clics++ }).
    // TODO 3: dentro del Box, un Text con el número de clics.
    Pendiente()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Alumno41() = Bloque(
    ALUMNO,
    "Con Modifier.combinedClickable: un clic suma 1, una pulsación larga pone el " +
        "contador a 0 y un doble clic suma 10. Muestra qué evento llegó."
) {
    // TODO 1: estados 'cuenta' (Int) y 'ultimo' (String con el nombre del evento).
    // TODO 2: Box con Modifier.combinedClickable(onClick = {...}, onDoubleClick = {...}, onLongClick = {...}).
    // TODO 3: muestra la cuenta dentro del Box y el último evento en Registro().
    Pendiente()
}
