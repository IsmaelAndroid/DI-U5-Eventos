# DI · U5.1 Explotación de componentes: **eventos** (Jetpack Compose)

Proyecto de Android Studio para practicar los apartados **3, 4 y 5** de la
[teoría U5.1](https://revilofe.github.io/section6/u05/teoria/DI-U5.1.-ExplotacionDeComponentes/):

- **3**: qué eventos tiene cada componente (Button, Switch, TextField, Slider, componente propio, componente de dibujo)
- **4**: tipos de evento y el `Modifier` que los captura (acción, teclado, puntero, hover, arrastre, foco, cambio de valor)
- **5**: foco y teclado físico

Cada tema es **un único fichero `.kt`** con la teoría comentada arriba y tres partes, que la app muestra en tres pestañas:

| Pestaña | Qué es |
|---|---|
| **Ejemplo** | Resuelto y comentado. Se explica en clase. |
| **Profe** | Ejercicio parecido que se resuelve en directo en clase (viene con `TODO`). |
| **Alumno** | Ejercicio para hacerlo de forma individual (viene con `TODO`). |

## Temas

| Tema | Componente / tipo | Eventos | Fichero |
|---|---|---|---|
| 3.1 | Button | `onClick` | `apartado3/T31_Button.kt` |
| 3.2 | Switch | `onCheckedChange` | `apartado3/T32_Switch.kt` |
| 3.3 | OutlinedTextField | `onValueChange`, `KeyboardActions` | `apartado3/T33_TextField.kt` |
| 3.4 | Slider | `onValueChange`, `onValueChangeFinished` | `apartado3/T34_Slider.kt` |
| 3.5 | Componente propio | `onPulsar`, `onSoltar` (lambdas en la firma) | `apartado3/T35_ComponentePropio.kt` |
| 3.6 | Componente de dibujo | sin eventos (solo parámetros, `Canvas`) | `apartado3/T36_ComponenteDibujo.kt` |
| 4.1 | Acción | `Modifier.clickable`, `combinedClickable` | `apartado4/T41_Clickable.kt` |
| 4.2 | Teclado | `onKeyEvent`, `onPreviewKeyEvent` | `apartado4/T42_Teclado.kt` |
| 4.3 | Puntero pulsar/soltar | `pointerInput { awaitEachGesture }` | `apartado4/T43_PulsarSoltar.kt` |
| 4.4 | Puntero entrar/salir | `Modifier.hoverable` | `apartado4/T44_Hover.kt` |
| 4.5 | Arrastre | `detectDragGestures` | `apartado4/T45_Arrastre.kt` |
| 4.6 | Foco | `onFocusEvent`, `FocusRequester` | `apartado4/T46_Foco.kt` |
| 4.7 | Cambio de valor | `Checkbox`, `RadioButton` | `apartado4/T47_CambioValor.kt` |
| 5 | Foco y teclado físico | `focusable`, `onPreviewKeyEvent`, `focusProperties` | `apartado5/T5_FocoTeclado.kt` |

Rutas relativas a `app/src/main/java/es/di/eventos/`.

## Cómo hacer un ejercicio

1. Abre el fichero del tema y busca la función `ProfeXX()` o `AlumnoXX()`.
2. Lee el enunciado (también aparece en la app) y los comentarios `TODO`.
3. Borra la línea `Pendiente()` y escribe tu código en su lugar.
4. Ejecuta la app, entra en el tema y abre la pestaña correspondiente.

## Consejos para el emulador

- **Teclado (4.2 y 5):** el teclado en pantalla no envía `KeyEvent`. Usa el teclado del PC
  (en el AVD: *Settings → Enable keyboard input*).
- **Hover (4.4):** pasa el **ratón del PC** por encima del emulador.
- Abrir un tema directamente:
  ```bash
  adb shell am start -n es.di.eventos/.MainActivity --es tema 4.5 --ei pestana 0
  ```

## Requisitos

Android Studio reciente (AGP 9.4, Kotlin 2.2, Compose BOM 2026.02), `minSdk 24`.
