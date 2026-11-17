package es.di.eventos.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp

/*
 * Piezas comunes de la app. NO forman parte de la teoría: solo sirven
 * para que todas las pantallas tengan el mismo aspecto.
 */

const val EJEMPLO = "✅ Ejemplo resuelto"
const val PROFE = "👩‍🏫 Ejercicio para el profe (en directo)"
const val ALUMNO = "🧑‍💻 Ejercicio para el alumnado"

/** Caja con título + enunciado + contenido. Cada ejemplo/ejercicio va dentro de una. */
@Composable
fun Bloque(titulo: String, enunciado: String, contenido: @Composable ColumnScope.() -> Unit) {
    Column(Modifier.fillMaxWidth().padding(16.dp)) {
        Text(titulo, style = MaterialTheme.typography.titleLarge)
        Text(enunciado, style = MaterialTheme.typography.bodyMedium, color = Color.DarkGray)
        Spacer(Modifier.height(16.dp))
        contenido()
    }
}

/** Muestra el último evento recibido. Es nuestro "println" visual. */
@Composable
fun Registro(texto: String) {
    Card(
        Modifier.fillMaxWidth().padding(top = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF263238))
    ) {
        Text(
            "▶ $texto",
            Modifier.padding(12.dp),
            color = Color(0xFFB2FF59),
            fontFamily = FontFamily.Monospace
        )
    }
}

/** Marcador para los ejercicios sin resolver. Bórralo cuando hagas el ejercicio. */
@Composable
fun Pendiente() {
    Text("⏳ Ejercicio pendiente: abre el fichero y busca los TODO", color = Color(0xFFE65100))
}
