package es.di.eventos.apartado3

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import es.di.eventos.ui.*

/* ═══════════════════════════════════════════════════════════════════════
 * 3.6  COMPONENTE DE DIBUJO SIN EVENTOS  ·  solo recibe parámetros
 * ═══════════════════════════════════════════════════════════════════════
 * TEORÍA
 *
 *  No todos los componentes necesitan eventos. Un componente que solo
 *  PINTA (como FiguraRaton en la teoría) recibe parámetros y dibuja.
 *
 *  Idea clave: un componente de dibujo NO necesita eventos ni estado propio.
 *  Si le metiéramos estado dentro crearíamos una SEGUNDA FUENTE DE VERDAD
 *  (el valor de fuera y el de dentro podrían no coincidir).
 *
 *  Los eventos los generan OTROS componentes (Slider, Button...), que
 *  cambian el estado, y el dibujo se repinta solo al recomponer.
 *  Para dibujar usamos Canvas { ...DrawScope... }.
 * ═══════════════════════════════════════════════════════════════════════ */

/** Componente de dibujo: ni eventos, ni estado. Solo pinta 'nivel' (0..1). */
@Composable
fun Bateria(nivel: Float, modifier: Modifier = Modifier) {
    Canvas(modifier.size(160.dp, 70.dp)) {
        val color = if (nivel < 0.2f) Color.Red else Color(0xFF4CAF50)
        drawRoundRect(Color.Black, size = Size(size.width - 12f, size.height),
            cornerRadius = CornerRadius(12f), style = Stroke(6f))
        drawRect(Color.Black, Offset(size.width - 12f, size.height / 3), Size(12f, size.height / 3))
        drawRect(color, Offset(10f, 10f), Size((size.width - 32f) * nivel, size.height - 20f))
    }
}

@Composable
fun Ejemplo36() = Bloque(
    EJEMPLO,
    "Bateria(nivel) es un componente de dibujo sin eventos. El Slider genera el evento, " +
        "cambia el estado y la batería se repinta sola."
) {
    var nivel by remember { mutableFloatStateOf(0.6f) }   // ÚNICA fuente de verdad

    Bateria(nivel)                                          // solo recibe
    Slider(nivel, onValueChange = { nivel = it }, modifier = Modifier.testTag("slider"))  // el evento está aquí
    Registro("nivel = ${(nivel * 100).toInt()} %")
}

@Composable
fun Profe36() = Bloque(
    PROFE,
    "Crea Semaforo(encendida: Int) dibujado con Canvas (0 rojo, 1 ámbar, 2 verde). " +
        "Un botón 'Siguiente' cambia la luz."
) {
    // TODO 1: FUERA de esta función crea @Composable fun Semaforo(encendida: Int) con un Canvas:
    //         un drawRoundRect gris de fondo y 3 drawCircle (apagados con alpha = 0.2f).
    // TODO 2: aquí, un estado 'luz' (Int).
    // TODO 3: Button "Siguiente": luz = (luz + 1) % 3.
    Pendiente()
}

@Composable
fun Alumno36() = Bloque(
    ALUMNO,
    "Crea BarraProgreso(porcentaje: Int) con Canvas (fondo gris + barra azul). " +
        "Dos botones '−10' y '+10' cambian el porcentaje (0..100)."
) {
    // TODO 1: FUERA crea BarraProgreso(porcentaje: Int): Canvas con drawRect gris y otro azul
    //         de ancho size.width * porcentaje / 100f.
    // TODO 2: estado 'pct' y dos botones que lo cambian con coerceIn(0, 100).
    Pendiente()
}
