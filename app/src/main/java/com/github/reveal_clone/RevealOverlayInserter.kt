package com.github.reveal_clone

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.DpOffset

/**
 * Defines a strategy of how to insert the overlay into the composition.
 */
@Stable
public interface RevealOverlayInserter {

	/**
	 * Container which is used to insert the overlay.
	 *
	 * @param content Overlay composable
	 */
	@Composable
	public fun Container(content: @Composable () -> Unit)

	/**
	 * Additional offset that is applied to all revealables when this inserter is used.
	 */
	public val revealableOffset: DpOffset
}
