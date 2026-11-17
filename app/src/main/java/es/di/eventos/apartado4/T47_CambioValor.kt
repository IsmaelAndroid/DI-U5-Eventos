package es.di.eventos.apartado4

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import es.di.eventos.ui.*

/* ═══════════════════════════════════════════════════════════════════════
 * 4.7  CAMBIO DE VALOR  ·  onCheckedChange (Checkbox) · onClick (RadioButton)
 * ═══════════════════════════════════════════════════════════════════════
 * TEORÍA
 *
 *  Los "eventos de cambio de valor" avisan de que el VALOR de un control
 *  ha cambiado: onValueChange (Slider, TextField, ya vistos en 3.3 y 3.4),
 *  onCheckedChange (Checkbox, Switch) y onClick de RadioButton.
 *
 *  Patrón común a todos (componente CONTROLADO):
 *      Control(valor = estado, onCambio = { nuevo -> estado = nuevo })
 *  El control NUNCA cambia solo: si no actualizas el estado, no se mueve.
 *
 *  Truco de usabilidad: hacer pulsable TODA la fila (no solo la cajita)
 *  con Modifier.toggleable / selectable, y pasar onCheckedChange = null
 *  al Checkbox para que el evento lo gestione la fila.
 * ═══════════════════════════════════════════════════════════════════════ */

@Composable
fun Ejemplo47() = Bloque(
    EJEMPLO,
    "Checkbox 'Acepto las condiciones': el botón 'Registrarme' solo se activa si está marcado."
) {
    var acepto by remember { mutableStateOf(false) }
    var registrado by remember { mutableStateOf(false) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = acepto,                              // valor que ENTRA
            onCheckedChange = { acepto = it },             // ← evento de cambio de valor
            modifier = Modifier.testTag("acepto")
        )
        Text("Acepto las condiciones")
    }
    Button(onClick = { registrado = true }, enabled = acepto) { Text("Registrarme") }
    Registro("acepto=$acepto · registrado=$registrado")
}

@Composable
fun Profe47() = Bloque(
    PROFE,
    "Pizza a medida: 3 Checkbox de ingredientes con precio (Queso 1 €, Champiñón 1,5 €, " +
        "Jamón 2 €) sobre una base de 6 €. Se muestra el total en cada cambio."
) {
    // TODO 1: mapa de precios y estado 'elegidos' de tipo Set<String>.
    // TODO 2: por cada ingrediente una Row con Checkbox + Text.
    //         onCheckedChange: elegidos = if (marcado) elegidos + ing else elegidos - ing
    // TODO 3: total = 6.0 + suma de precios elegidos. Muéstralo con Registro().
    Pendiente()
}

@Composable
fun Alumno47() = Bloque(
    ALUMNO,
    "Pedido de café: grupo de RadioButton para el tamaño (Pequeño, Mediano, Grande) con " +
        "TODA la fila pulsable (Modifier.selectable) y un Checkbox 'Para llevar'. Muestra el resumen."
) {
    // TODO 1: lista de tamaños, estado 'tamano' (String) y 'llevar' (Boolean).
    // TODO 2: por cada tamaño una Row con Modifier.selectable(selected, onClick = { tamano = t }, role = Role.RadioButton)
    //         y dentro RadioButton(selected = ..., onClick = null) + Text.
    // TODO 3: Checkbox "Para llevar" con onCheckedChange.
    // TODO 4: Registro con el resumen del pedido.
    Pendiente()
}
