package es.di.eventos.apartado5

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusRestorer
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import es.di.eventos.ui.*

/* ═══════════════════════════════════════════════════════════════════════
 * 5  FOCO Y TECLADO FÍSICO
 * ═══════════════════════════════════════════════════════════════════════
 * TEORÍA
 *
 *  En móvil el foco importa cuando hay mando (Android TV), teclado Bluetooth,
 *  Chromebook o servicios de accesibilidad. El usuario se mueve con Tab o
 *  con las flechas (DPAD) y "pulsa" con Enter.
 *
 *  Un componente propio que quiera funcionar con teclado necesita:
 *    1) Ser enfocable:            Modifier.focusable()
 *    2) Mostrar que tiene foco:   Modifier.onFocusEvent { foco = it.hasFocus }
 *    3) Reaccionar a Enter:       Modifier.onPreviewKeyEvent { e ->
 *                                     if (e.key == Key.Enter && e.type == KeyEventType.KeyUp) {
 *                                         onPulsar(); true } else false }
 *
 *  Además:
 *    - FocusRequester pide el foco (p. ej. al primer elemento).
 *    - focusRestorer(): al volver al grupo, recupera el último foco
 *      (útil tras una recomposición o al volver de otra fila).
 *    - El recorrido del foco sigue el ORDEN DE DECLARACIÓN. Si no coincide
 *      con el orden visual se corrige con focusProperties { next = ... }
 *      o agrupando con focusGroup().
 * ═══════════════════════════════════════════════════════════════════════ */

/** Tecla accesible con teclado (versión del fragmento del apartado 5). */
@Composable
fun TeclaFoco(nota: String, onPulsar: (String) -> Unit, modifier: Modifier = Modifier) {
    var foco by remember { mutableStateOf(false) }
    Box(
        modifier
            .size(64.dp, 120.dp)
            .background(if (foco) Color(0xFFFFF176) else Color.White)
            .border(if (foco) 4.dp else 1.dp, if (foco) Color.Blue else Color.Black)
            .onFocusEvent { foco = it.hasFocus }                       // 2) ver el foco
            .onPreviewKeyEvent { e ->                                  // 3) Enter = pulsar
                if (e.key == Key.Enter && e.type == KeyEventType.KeyUp) { onPulsar(nota); true } else false
            }
            .focusable(),                                              // 1) enfocable
        contentAlignment = Alignment.BottomCenter
    ) { Text(nota, Modifier.padding(8.dp)) }
}

@Composable
fun Ejemplo5() = Bloque(
    EJEMPLO,
    "Piano manejable SOLO con teclado: 'Empezar' da el foco a la primera tecla; " +
        "muévete con Tab o ← → y pulsa Enter para tocar la nota."
) {
    val primera = remember { FocusRequester() }
    var tocada by remember { mutableStateOf("-") }

    Row(
        Modifier.focusRestorer(),            // al volver a la fila, recupera la última tecla
        horizontalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        listOf("Do", "Re", "Mi", "Fa").forEachIndexed { i, nota ->
            TeclaFoco(nota, onPulsar = { tocada = it },
                modifier = (if (i == 0) Modifier.focusRequester(primera) else Modifier).testTag("tecla$i"))
        }
    }
    Button(onClick = { primera.requestFocus() }, Modifier.padding(top = 8.dp).testTag("empezar")) { Text("Empezar") }
    Registro("Enter -> nota tocada: $tocada")
}

@Composable
fun Profe5() = Bloque(
    PROFE,
    "Menú tipo Android TV: 4 opciones (Películas, Series, Música, Ajustes) que se recorren " +
        "con ↑ ↓. La opción con foco se resalta y crece; Enter la elige."
) {
    // TODO 1: crea (fuera) OpcionMenu(texto, onElegir) con: estado 'foco', onFocusEvent,
    //         onPreviewKeyEvent (Enter o DirectionCenter en KeyUp -> onElegir()) y focusable().
    //         Si tiene foco: fondo azul y padding mayor.
    // TODO 2: aquí una Column(Modifier.focusRestorer()) con las 4 opciones.
    // TODO 3: FocusRequester en la primera y Button "Empezar" que pida el foco.
    Pendiente()
}

@Composable
fun Alumno5() = Bloque(
    ALUMNO,
    "Rejilla 2×2 de botones A B / C D declarada por filas. Con Tab el foco va A→B→C→D. " +
        "Cambia el orden para que vaya por COLUMNAS: A→C→B→D, usando focusProperties { next = ... }."
) {
    // TODO 1: crea 4 FocusRequester (a, b, c, d).
    // TODO 2: dos Row: [A, B] y [C, D] con OutlinedButton y Modifier.focusRequester(...).
    // TODO 3: añade .focusProperties { next = ...; previous = ... } para que Tab vaya A→C→B→D.
    // TODO 4: Button "Foco en A" que llame a a.requestFocus().
    Pendiente()
}
