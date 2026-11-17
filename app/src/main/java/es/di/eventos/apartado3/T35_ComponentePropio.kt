package es.di.eventos.apartado3

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import es.di.eventos.ui.*

/* ═══════════════════════════════════════════════════════════════════════
 * 3.5  COMPONENTE PROPIO CON EVENTOS  ·  onPulsar / onSoltar
 * ═══════════════════════════════════════════════════════════════════════
 * TEORÍA
 *
 *  Cuando creamos NUESTRO componente, sus eventos se declaran en la FIRMA
 *  de la función como lambdas. Ese es el "contrato" del componente:
 *
 *      fun Tecla(nota: String, presionada: Boolean,
 *                onPulsar: (String) -> Unit, onSoltar: (String) -> Unit)
 *
 *  - Lo que ENTRA (nota, presionada) son propiedades.
 *  - Lo que SALE (onPulsar, onSoltar) son eventos.
 *  - El componente NO decide qué pasa: solo avisa. Quien lo usa decide.
 *    (igual que en PanelPuntero(configuracion, onCambio) de la teoría).
 *
 *  Por convenio los eventos se llaman onAlgo y el componente es "sin estado"
 *  (stateless): el estado vive fuera, en quien lo usa (state hoisting).
 * ═══════════════════════════════════════════════════════════════════════ */

/** Componente propio del tema (versión reducida de Tecla.kt de la teoría). */
@Composable
fun Tecla(
    nota: String,
    presionada: Boolean,                 // propiedad (entra)
    onPulsar: (String) -> Unit,          // evento (sale)
    onSoltar: (String) -> Unit,          // evento (sale)
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .size(56.dp, 140.dp)
            .background(if (presionada) Color(0xFFFFF176) else Color.White)
            .border(1.dp, Color.Black)
            .semantics { contentDescription = "Tecla $nota" }
            // El gesto pulsar/soltar se ve en detalle en el apartado 4.3.
            .pointerInput(nota) {
                awaitEachGesture {
                    awaitFirstDown(requireUnconsumed = false)
                    onPulsar(nota)                 // EMITIMOS el evento propio
                    waitForUpOrCancellation()
                    onSoltar(nota)                 // EMITIMOS el evento propio
                }
            },
        contentAlignment = Alignment.BottomCenter
    ) { Text(nota, Modifier.padding(8.dp)) }
}

@Composable
fun Ejemplo35() = Bloque(
    EJEMPLO,
    "Mini piano con el componente propio Tecla. El piano decide qué hacer con " +
        "los eventos onPulsar y onSoltar (aquí: marcar la tecla y anotar la nota)."
) {
    // El estado vive FUERA del componente Tecla.
    var pulsada by remember { mutableStateOf<String?>(null) }
    var historial by remember { mutableStateOf("") }

    Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
        listOf("Do", "Re", "Mi", "Fa", "Sol").forEach { nota ->
            Tecla(
                nota = nota,
                presionada = pulsada == nota,
                onPulsar = { pulsada = it; historial = (historial + " " + it).takeLast(30) },
                onSoltar = { pulsada = null },
                modifier = Modifier.testTag("tecla-$nota")
            )
        }
    }
    Registro("Pulsada: ${pulsada ?: "-"}  | Historial:$historial")
}

@Composable
fun Profe35() = Bloque(
    PROFE,
    "Crea el componente propio Contador(etiqueta, valor, onIncrementar, onDecrementar) " +
        "y úsalo dos veces: 'Adultos' y 'Niños'. Muestra el total de entradas."
) {
    // TODO 1: FUERA de esta función, crea @Composable fun Contador(etiqueta: String, valor: Int,
    //         onIncrementar: () -> Unit, onDecrementar: () -> Unit). Dentro: Row con Text y dos botones.
    // TODO 2: aquí dentro, dos estados: adultos y ninos.
    // TODO 3: usa Contador dos veces pasando las lambdas { adultos++ }, { adultos-- }...
    // TODO 4: Registro("Total entradas: ...").
    Pendiente()
}

@Composable
fun Alumno35() = Bloque(
    ALUMNO,
    "Crea el componente propio SelectorColor(colores, seleccionado, onSeleccionar: (Color) -> Unit) " +
        "con círculos de colores. Úsalo para pintar un cuadrado grande del color elegido."
) {
    // TODO 1: FUERA de esta función crea SelectorColor(colores: List<Color>, seleccionado: Color,
    //         onSeleccionar: (Color) -> Unit). Pinta un círculo por color con Modifier.clickable { onSeleccionar(c) }.
    // TODO 2: aquí, un estado 'color'.
    // TODO 3: usa SelectorColor y debajo un Box(Modifier.size(120.dp).background(color)).
    Pendiente()
}
