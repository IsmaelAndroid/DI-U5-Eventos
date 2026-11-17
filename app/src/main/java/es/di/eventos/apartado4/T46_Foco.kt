package es.di.eventos.apartado4

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import es.di.eventos.ui.*

/* ═══════════════════════════════════════════════════════════════════════
 * 4.6  FOCO  ·  Modifier.onFocusEvent  +  FocusRequester
 * ═══════════════════════════════════════════════════════════════════════
 * TEORÍA
 *
 *  El FOCO indica qué componente recibe el teclado. Solo uno lo tiene.
 *
 *  - Escuchar el foco:   Modifier.onFocusEvent { estado -> estado.hasFocus }
 *                        (hasFocus = él o un hijo tiene el foco;
 *                         isFocused = exactamente él). También existe onFocusChanged.
 *  - Pedir el foco:      val fr = remember { FocusRequester() }
 *                        Modifier.focusRequester(fr)   ...   fr.requestFocus()
 *  - Quitar el foco:     LocalFocusManager.current.clearFocus()  (oculta el teclado)
 *
 *  Uso típico: resaltar el campo activo, mostrar ayudas solo mientras se
 *  escribe, validar cuando el usuario SALE de un campo (pierde el foco).
 * ═══════════════════════════════════════════════════════════════════════ */

@Composable
fun Ejemplo46() = Bloque(
    EJEMPLO,
    "Dos campos. onFocusEvent indica cuál tiene el foco. El botón 'Ir a email' " +
        "mueve el foco con un FocusRequester."
) {
    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var activo by remember { mutableStateOf("ninguno") }
    val focoEmail = remember { FocusRequester() }

    OutlinedTextField(
        nombre, { nombre = it }, label = { Text("Nombre") },
        modifier = Modifier
            .onFocusEvent { if (it.hasFocus) activo = "Nombre" }      // ← evento de foco
            .testTag("nombre")
    )
    OutlinedTextField(
        email, { email = it }, label = { Text("Email") },
        modifier = Modifier
            .focusRequester(focoEmail)                                // para pedirlo desde fuera
            .onFocusEvent { if (it.hasFocus) activo = "Email" }
    )
    Button(onClick = { focoEmail.requestFocus() }, Modifier.padding(top = 8.dp).testTag("irEmail")) {
        Text("Ir a email")
    }
    Registro("Foco en: $activo")
}

@Composable
fun Profe46() = Bloque(
    PROFE,
    "Formulario de 3 campos (Nombre, Apellidos, Teléfono). Al pulsar 'Siguiente' en el " +
        "teclado el foco salta al siguiente campo; en el último, 'Hecho' quita el foco."
) {
    // TODO 1: crea dos FocusRequester (apellidos y teléfono) y obtén LocalFocusManager.current.
    // TODO 2: tres OutlinedTextField. Los dos primeros con ImeAction.Next y
    //         KeyboardActions(onNext = { siguiente.requestFocus() }).
    // TODO 3: el último con ImeAction.Done y onDone = { focusManager.clearFocus() }.
    // TODO 4: con onFocusEvent muestra en Registro qué campo está activo.
    Pendiente()
}

@Composable
fun Alumno46() = Bloque(
    ALUMNO,
    "Campo 'Contraseña' que solo muestra la ayuda 'Mínimo 8 caracteres' MIENTRAS tiene el foco. " +
        "Al perder el foco valida y dice si es válida. Botón 'Limpiar' que borra y vuelve a dar el foco."
) {
    // TODO 1: estados 'clave', 'conFoco' y 'validacion', y un FocusRequester.
    // TODO 2: OutlinedTextField con supportingText = { if (conFoco) Text("Mínimo 8 caracteres") }.
    // TODO 3: onFocusEvent: si antes tenía foco y ahora no -> valida (length >= 8). Guarda conFoco = it.hasFocus.
    // TODO 4: Button "Limpiar": clave = "" y foco.requestFocus().
    Pendiente()
}
