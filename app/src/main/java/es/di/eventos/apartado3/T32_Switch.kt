package es.di.eventos.apartado3

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import es.di.eventos.ui.*

/* ═══════════════════════════════════════════════════════════════════════
 * 3.2  SWITCH  ·  evento: onCheckedChange(activada: Boolean)
 * ═══════════════════════════════════════════════════════════════════════
 * TEORÍA
 *
 *  El Switch NO guarda si está encendido: lo recibe por parámetro (checked)
 *  y, cuando el usuario lo toca, nos AVISA con onCheckedChange(nuevoValor).
 *  Si no actualizamos el estado, el Switch no se mueve (componente controlado).
 *
 *  Acción típica (tabla 3.1): COPIAR la configuración con el booleano
 *  cambiado. Guardamos los ajustes en una data class INMUTABLE y usamos
 *  copy(): nunca se modifica el objeto, se crea uno nuevo.
 *      ajustes = ajustes.copy(wifi = activada)
 * ═══════════════════════════════════════════════════════════════════════ */

/** Configuración inmutable (apartado 7: data class + copy). */
data class Ajustes(val wifi: Boolean = false, val bluetooth: Boolean = true)

@Composable
fun Ejemplo32() = Bloque(
    EJEMPLO,
    "Dos interruptores (Wi-Fi y Bluetooth) que modifican una configuración inmutable."
) {
    var ajustes by remember { mutableStateOf(Ajustes()) }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Wi-Fi", Modifier.weight(1f))
        Switch(
            checked = ajustes.wifi,                                   // el estado ENTRA
            onCheckedChange = { activada ->                           // el evento SALE
                ajustes = ajustes.copy(wifi = activada)               // copiamos con el cambio
            },
            modifier = Modifier.testTag("wifi")
        )
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Bluetooth", Modifier.weight(1f))
        Switch(
            checked = ajustes.bluetooth,
            onCheckedChange = { ajustes = ajustes.copy(bluetooth = it) }  // 'it' = nuevo valor
        )
    }
    Registro(ajustes.toString())
}

@Composable
fun Profe32() = Bloque(
    PROFE,
    "Switch 'Notificaciones' y Switch 'Sonido'. Si se apagan las notificaciones, " +
        "el de sonido se desactiva (enabled = false) y también se apaga."
) {
    // TODO 1: crea dos estados booleanos: notificaciones y sonido (ambos true).
    // TODO 2: Switch de notificaciones: en onCheckedChange guarda el valor y,
    //         si se apaga, pon también sonido = false.
    // TODO 3: Switch de sonido con enabled = notificaciones.
    // TODO 4: muestra ambos valores con Registro().
    Pendiente()
}

@Composable
fun Alumno32() = Bloque(
    ALUMNO,
    "Dos Switch 'Mayúsculas' y 'Negrita' que cambian el estilo del texto 'Hola DAM'. " +
        "Usa una data class Estilo(mayus, negrita) y copy()."
) {
    // TODO 1: define data class Estilo(val mayus: Boolean = false, val negrita: Boolean = false)
    //         y un estado 'estilo' con remember { mutableStateOf(Estilo()) }.
    // TODO 2: dos filas Text + Switch. En onCheckedChange usa estilo.copy(...).
    // TODO 3: pinta "Hola DAM" en mayúsculas/negrita según el estado (fontWeight = FontWeight.Bold).
    Pendiente()
}
