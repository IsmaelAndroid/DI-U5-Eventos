package es.di.eventos.apartado3

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import es.di.eventos.ui.*

/* ═══════════════════════════════════════════════════════════════════════
 * 3.1  BUTTON  ·  evento: onClick
 * ═══════════════════════════════════════════════════════════════════════
 * TEORÍA (apartado 3 · Eventos: clases, componentes y métodos)
 *
 *  Todo evento tiene tres piezas:
 *    - FUENTE  : el componente que lo genera (aquí, el Button).
 *    - EVENTO  : lo que ha pasado (el usuario ha pulsado).
 *    - OYENTE  : el código que reacciona.
 *
 *  En Compose NO se "registran" oyentes (no hay setOnClickListener):
 *  el oyente es un PARÁMETRO de la función, una lambda.
 *      Button(onClick = { ...esto es el oyente... }) { ... }
 *
 *  El Button no cambia nada por dentro: solo AVISA. Quien lo usa decide
 *  qué estado cambia. Al cambiar el estado, Compose RECOMPONE y la
 *  pantalla se redibuja sola.
 *
 *  Acción típica de onClick (tabla 3.1): emitir el evento con el estado nuevo.
 * ═══════════════════════════════════════════════════════════════════════ */

@Composable
fun Ejemplo31() = Bloque(
    EJEMPLO,
    "Botón 'Me gusta': cada pulsación suma 1. Al llegar a 5 el botón se desactiva."
) {
    // 1) ESTADO: la "fuente de verdad". remember lo conserva entre recomposiciones.
    var likes by remember { mutableIntStateOf(0) }

    // 2) FUENTE del evento: el Button.
    Button(
        // 3) OYENTE: la lambda onClick. Se ejecuta cada vez que se pulsa.
        onClick = { likes++ },          // cambiar el estado provoca la recomposición
        enabled = likes < 5,            // propiedad que depende del estado
        modifier = Modifier.testTag("boton")
    ) {
        Text("👍 Me gusta ($likes)")
    }

    // 4) La interfaz es una FUNCIÓN del estado: se redibuja sola.
    Registro(if (likes < 5) "onClick recibido $likes veces" else "Límite alcanzado: botón desactivado")
}

@Composable
fun Profe31() = Bloque(
    PROFE,
    "Control de volumen con dos botones '−' y '+'. El volumen va de 0 a 10 y " +
        "cada botón se desactiva al llegar a su límite."
) {
    // TODO 1: crea un estado entero 'volumen' que empiece en 5 (remember + mutableIntStateOf).
    // TODO 2: dentro de una Row pon Button("−"), un Text con el volumen y Button("+").
    // TODO 3: en cada onClick resta o suma 1. Usa 'enabled' para no salir de 0..10.
    // TODO 4: muestra el volumen con Registro("...").
    Pendiente()
}

@Composable
fun Alumno31() = Bloque(
    ALUMNO,
    "Botón 'Lanzar dado': al pulsarlo muestra un número aleatorio del 1 al 6 y " +
        "cuántas veces se ha lanzado. Añade un segundo botón 'Reiniciar' que lo ponga todo a 0."
) {
    // TODO 1: crea dos estados: 'valor' (último número) y 'tiradas' (contador).
    // TODO 2: Button "Lanzar dado" -> valor = (1..6).random() y tiradas++.
    // TODO 3: OutlinedButton "Reiniciar" -> pon ambos estados a 0.
    // TODO 4: muestra el valor en grande y las tiradas con Registro().
    Pendiente()
}
