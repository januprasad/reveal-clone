package com.github.reveal_clone.effect

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.github.reveal_clone.ActualRevealable
import com.github.reveal_clone.Key
import com.github.reveal_clone.RevealOverlayScope
import com.github.reveal_clone.RevealState

/**
 * Overlay effect which renders the background and reveal effect.
 */
public interface RevealOverlayEffect {

	@Composable
	public fun Overlay(
		revealState: RevealState,
		currentRevealable: State<ActualRevealable?>,
		previousRevealable: State<ActualRevealable?>,
		modifier: Modifier,
		content: @Composable RevealOverlayScope.(key: Key) -> Unit,
	)

	/**
	 * Animation spec for the animated alpha of the overlay when this effect is shown or hidden.
	 */
	public val alphaAnimationSpec: AnimationSpec<Float>
}
