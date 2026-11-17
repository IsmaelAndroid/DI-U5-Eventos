package es.di.eventos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp

/*
 * Punto de entrada. Muestra un menú con todos los temas (apartados 3, 4 y 5)
 * y, al elegir uno, tres pestañas: Ejemplo resuelto · Ejercicio profe · Ejercicio alumno.
 *
 * Los temas se registran en Temas.kt.
 *
 * Truco para clase: se puede abrir un tema directamente con adb:
 *   adb shell am start -n es.di.eventos/.MainActivity --es tema 4.5 --ei pestana 0
 */
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temaInicial = intent.getStringExtra("tema")
        val pestanaInicial = intent.getIntExtra("pestana", 0)
        setContent {
            MaterialTheme {
                Surface(
                    Modifier.fillMaxSize().safeDrawingPadding()
                        // Expone los testTag como resource-id (útil para uiautomator / Layout Inspector).
                        .semantics { testTagsAsResourceId = true }
                ) {
                    App(temaInicial, pestanaInicial)
                }
            }
        }
    }
}

@Composable
fun App(temaInicial: String?, pestanaInicial: Int) {
    // rememberSaveable: el tema elegido sobrevive a girar la pantalla (apartado 2.2).
    var temaId by rememberSaveable { mutableStateOf(temaInicial) }
    val tema = TEMAS.find { it.id == temaId }

    if (tema == null) {
        Menu(onElegir = { temaId = it.id })
    } else {
        BackHandler { temaId = null }
        PantallaTema(tema, pestanaInicial)
    }
}

@Composable
fun Menu(onElegir: (Tema) -> Unit) {
    LazyColumn {
        item {
            Text("U5.1 · Explotación de componentes: eventos", Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall)
        }
        items(TEMAS) { tema ->
            ListItem(
                headlineContent = { Text("${tema.id}  ${tema.titulo}") },
                supportingContent = { Text(tema.eventos) },
                modifier = Modifier.clickable { onElegir(tema) }
            )
            HorizontalDivider()
        }
    }
}

@Composable
fun PantallaTema(tema: Tema, pestanaInicial: Int) {
    var pestana by rememberSaveable { mutableIntStateOf(pestanaInicial) }
    Column(Modifier.fillMaxSize().imePadding()) {
        Text("${tema.id}  ${tema.titulo}", Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleMedium)
        TabRow(selectedTabIndex = pestana) {
            listOf("Ejemplo", "Profe", "Alumno").forEachIndexed { i, nombre ->
                Tab(selected = pestana == i, onClick = { pestana = i }, text = { Text(nombre) })
            }
        }
        Column(Modifier.verticalScroll(rememberScrollState())) {
            when (pestana) {
                0 -> tema.ejemplo()
                1 -> tema.profe()
                else -> tema.alumno()
            }
        }
    }
}
