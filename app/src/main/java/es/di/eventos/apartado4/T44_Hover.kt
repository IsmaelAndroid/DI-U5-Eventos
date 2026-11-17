package es.di.eventos.apartado4

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
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
 * 4.4  PUNTERO: ENTRAR Y SALIR (ratón)  ·  Modifier.hoverable
 * ═══════════════════════════════════════════════════════════════════════
 * TEORÍA
 *
 *  "Hover" = el puntero está ENCIMA del componente sin pulsar.
 *  Solo existe con ratón, trackpad o lápiz (tablets, Chromebook, emulador
 *  con el ratón del PC). En táctil casi no se usa: el dedo no "flota".
 *
 *  Cómo se captura:
 *    val interaccion = remember { MutableInteractionSource() }
 *    val encima by interaccion.collectIsHoveredAsState()   // true / false
 *    Modifier.hoverable(interaccion)
 *
 *  MutableInteractionSource es un "canal" por el que el componente publica
 *  sus interacciones (hover, press, focus, drag). collectIsHoveredAsState()
 *  lo convierte en un estado que provoca recomposición.
 *
 *  👉 Para probarlo en el emulador: mueve el ratón del PC por encima.
 * ═══════════════════════════════════════════════════════════════════════ */

@Composable
fun Ejemplo44() = Bloque(
    EJEMPLO,
    "Tarjeta que se ilumina cuando el ratón pasa por encima (prueba con el ratón del PC en el emulador)."
) {
    val interaccion = remember { MutableInteractionSource() }
    val encima by interaccion.collectIsHoveredAsState()       // estado derivado del evento

    Box(
        Modifier.size(220.dp, 120.dp)
            .background(if (encima) Color(0xFFFFEB3B) else Color(0xFFE0E0E0))
            .hoverable(interaccion)                            // ← evento entrar/salir
            .testTag("tarjeta"),
        contentAlignment = Alignment.Center
    ) { Text(if (encima) "🖱️ ¡Ratón encima!" else "Pasa el ratón") }
    Registro("isHovered = $encima")
}

@Composable
fun Profe44() = Bloque(
    PROFE,
    "Tres chips (Guardar, Abrir, Salir). Al pasar el ratón por uno se muestra debajo " +
        "su descripción, como un 'tooltip' casero."
) {
    // TODO 1: crea (fuera) un composable OpcionConAyuda(nombre, ayuda, onHover: (String?) -> Unit)
    //         con su MutableInteractionSource y collectIsHoveredAsState().
    // TODO 2: dentro usa LaunchedEffect(encima) { onHover(if (encima) ayuda else null) }.
    // TODO 3: aquí, un estado 'ayuda' y una Row con 3 OpcionConAyuda. Muestra 'ayuda' en Registro().
    Pendiente()
}

@Composable
fun Alumno44() = Bloque(
    ALUMNO,
    "Un cuadrado que crece de 100 a 150 dp cuando el ratón está encima " +
        "(animateDpAsState) y cuenta cuántas veces ha entrado el ratón."
) {
    // TODO 1: MutableInteractionSource + collectIsHoveredAsState().
    // TODO 2: estado 'entradas' y LaunchedEffect(encima) { if (encima) entradas++ }.
    // TODO 3: val lado by animateDpAsState(if (encima) 150.dp else 100.dp)
    // TODO 4: Box(Modifier.size(lado).background(...).hoverable(interaccion)).
    Pendiente()
}
