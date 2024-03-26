package com.github.reveal_clone

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.reveal_clone.shapes.balloon.Arrow
import com.github.reveal_clone.shapes.balloon.Balloon
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

private enum class Keys { Fab, Explanation, CreateAccount }

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MainScreen(revealCanvasState: RevealCanvasState, modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    val revealState = rememberRevealState()

    LaunchedEffect(Unit) {
        if (revealState.isVisible) return@LaunchedEffect
        delay(2.seconds)
        revealState.reveal(Keys.CreateAccount)
    }

    Reveal(
        onOverlayClick = { scope.launch { revealState.hide() } },
        modifier = modifier,
        revealCanvasState = revealCanvasState,
        revealState = revealState,
        overlayContent = { key -> RevealOverlayContent(key) },
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Reveal Demo") },
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.revealable(
                        key = Keys.Fab,
                        shape = RevealShape.RoundRect(16.dp),
                        onClick = {
                            scope.launch { revealState.reveal(Keys.Explanation) }
                        },
                    ),
                    onClick = {
                        scope.launch { revealState.reveal(Keys.Explanation) }
                    },
                ) {
                    Icon(
                        Icons.Filled.Add,
                        contentDescription = null,
                    )
                }
            },
        ) { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .padding(horizontal = 16.dp),
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .revealable(
                            key = Keys.Explanation,
                            onClick = {
                                scope.launch { revealState.hide() }
                            },
                        ),
                    text = "Reveal is a lightweight, simple reveal effect (also known as " +
                        "coach mark or onboarding) library for Compose Multiplatform.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Justify,
                )
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomEnd,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .revealable(
                                key = Keys.CreateAccount,
                                onClick = {
                                    scope.launch { revealState.hide() }
                                },
                            ),
                        text = "create new account",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Justify,
                    )
                }
            }
        }
    }
}

@Composable
private fun RevealOverlayScope.RevealOverlayContent(key: Key) {
    when (key) {
        Keys.Fab -> OverlayText(
            modifier = Modifier.align(
                horizontalArrangement = RevealOverlayArrangement.Start,
            ),
            text = "Click button to get started",
            arrow = Arrow.end(),
        )

        Keys.Explanation -> OverlayText(
            modifier = Modifier.align(
                verticalArrangement = RevealOverlayArrangement.Bottom,
            ),
            text = "Actually...",
            arrow = Arrow.top(),
        )

        Keys.CreateAccount -> OverlayText(
            modifier = Modifier.align(
                verticalArrangement = RevealOverlayArrangement.Top,
            ),
            text = "Fully secure account, within 2 minutes",
            arrow = Arrow.bottom(),
        )
    }
}

@Composable
private fun OverlayText(text: String, arrow: Arrow, modifier: Modifier = Modifier) {
    Balloon(
        modifier = modifier.padding(8.dp),
        arrow = arrow,
        backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
        elevation = 2.dp,
    ) {
        Card {
            Column(Modifier.padding(10.dp)) {
                Text(text, textAlign = TextAlign.Center)
            }
        }
    }
}
