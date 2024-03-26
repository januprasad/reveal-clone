package com.github.reveal_clone

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.reveal_clone.ui.theme.RevealcloneTheme

@Composable
fun App(modifier: Modifier = Modifier) {
    RevealcloneTheme {
        val revealCanvasState = rememberRevealCanvasState()

        RevealCanvas(
            revealCanvasState = revealCanvasState,
            modifier = modifier,
        ) {
            MainScreen(revealCanvasState = revealCanvasState)
        }
    }
}
