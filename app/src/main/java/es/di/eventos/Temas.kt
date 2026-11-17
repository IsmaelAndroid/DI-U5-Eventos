package es.di.eventos

import androidx.compose.runtime.Composable
import es.di.eventos.apartado3.*
import es.di.eventos.apartado4.*
import es.di.eventos.apartado5.*

/*
 * Registro de temas. Cada tema tiene 3 partes:
 *   ejemplo -> RESUELTO y comentado (el profe lo explica)
 *   profe   -> ejercicio parecido que el profe resuelve en directo en clase
 *   alumno  -> ejercicio para que el alumnado lo haga solo
 */
data class Tema(
    val id: String,
    val titulo: String,
    val eventos: String,
    val ejemplo: @Composable () -> Unit,
    val profe: @Composable () -> Unit,
    val alumno: @Composable () -> Unit,
)

val TEMAS = listOf(
    // ── Apartado 3: qué eventos tiene cada componente ──
    Tema("3.1", "Button", "onClick", { Ejemplo31() }, { Profe31() }, { Alumno31() }),
    Tema("3.2", "Switch", "onCheckedChange", { Ejemplo32() }, { Profe32() }, { Alumno32() }),
    Tema("3.3", "OutlinedTextField", "onValueChange · acción de teclado", { Ejemplo33() }, { Profe33() }, { Alumno33() }),
    Tema("3.4", "Slider", "onValueChange · onValueChangeFinished", { Ejemplo34() }, { Profe34() }, { Alumno34() }),
    Tema("3.5", "Componente propio con eventos", "onPulsar · onSoltar (lambdas en la firma)", { Ejemplo35() }, { Profe35() }, { Alumno35() }),
    Tema("3.6", "Componente de dibujo sin eventos", "solo parámetros (Canvas)", { Ejemplo36() }, { Profe36() }, { Alumno36() }),
    // ── Apartado 4: tipos de evento y su Modifier ──
    Tema("4.1", "Acción (pulsar)", "Modifier.clickable · combinedClickable", { Ejemplo41() }, { Profe41() }, { Alumno41() }),
    Tema("4.2", "Teclado", "onKeyEvent · onPreviewKeyEvent", { Ejemplo42() }, { Profe42() }, { Alumno42() }),
    Tema("4.3", "Puntero: pulsar y soltar", "pointerInput · awaitEachGesture", { Ejemplo43() }, { Profe43() }, { Alumno43() }),
    Tema("4.4", "Puntero: entrar y salir (ratón)", "Modifier.hoverable", { Ejemplo44() }, { Profe44() }, { Alumno44() }),
    Tema("4.5", "Arrastre", "pointerInput · detectDragGestures", { Ejemplo45() }, { Profe45() }, { Alumno45() }),
    Tema("4.6", "Foco", "onFocusEvent · FocusRequester", { Ejemplo46() }, { Profe46() }, { Alumno46() }),
    Tema("4.7", "Cambio de valor", "Checkbox · RadioButton · onCheckedChange", { Ejemplo47() }, { Profe47() }, { Alumno47() }),
    // ── Apartado 5: foco y teclado físico ──
    Tema("5", "Foco y teclado físico", "onFocusEvent · onPreviewKeyEvent · focusProperties", { Ejemplo5() }, { Profe5() }, { Alumno5() }),
)
