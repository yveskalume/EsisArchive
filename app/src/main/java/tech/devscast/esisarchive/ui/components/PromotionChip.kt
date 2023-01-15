package tech.devscast.esisarchive.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import tech.devscast.esisarchive.data.entity.Promotion

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PromotionChip(
		onClick: () -> Unit,
		isChecked: Boolean,
		promotion: Promotion,
) {
		val color by animateColorAsState(
				targetValue = if (isChecked) MaterialTheme.colors.primary else Color.White
		)
		Chip(onClick = onClick, colors = ChipDefaults.chipColors(backgroundColor = color)) {
				Text(text = promotion.name)
		}
}