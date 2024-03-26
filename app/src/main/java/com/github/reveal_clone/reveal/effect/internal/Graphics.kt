package com.github.reveal_clone.effect.internal

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import com.github.reveal_clone.ActualRevealable

internal fun ActualRevealable.createShapePath(
	density: Density,
	layoutDirection: LayoutDirection,
): Path = shape
	.clip(
		size = Size(
			width = area.width,
			height = area.height,
		),
		density = density,
		layoutDirection = layoutDirection,
	)
	.apply {
		translate(
			Offset(
				x = area.left,
				y = area.top,
			),
		)
	}
