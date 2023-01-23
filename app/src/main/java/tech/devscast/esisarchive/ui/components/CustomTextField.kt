package tech.devscast.esisarchive.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.devscast.esisarchive.ui.theme.EsisArchiveTheme
import tech.devscast.esisarchive.ui.theme.Gray004


private val INPUT_ICON_SIZE = 24.dp

@Composable
fun CustomTextField(
		value: String,
		onValueChange: (String) -> Unit,
		modifier: Modifier = Modifier,
		placeholder: @Composable (() -> Unit)? = null,
		leadingIcon: @Composable (() -> Unit)? = null,
		trailingIcon: @Composable (() -> Unit)? = null,
) {
		OutlinedTextField(
				value = value,
				onValueChange = onValueChange,
				placeholder = placeholder,
				shape = RoundedCornerShape(16.dp),
				trailingIcon = trailingIcon,
				leadingIcon = leadingIcon,
				colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedBorderColor = Gray004),
				modifier = modifier
		)
}

@Composable
fun CustomTextField(
		value: String,
		onValueChange: (String) -> Unit,
		modifier: Modifier = Modifier,
		placeholder: @Composable (() -> Unit)? = null,
		leadingIcon: @Composable (() -> Unit)? = null,
		trailingIcon: @Composable (() -> Unit)? = null,
		colors: TextFieldColors = TextFieldDefaults.textFieldColors()
) {
		OutlinedTextField(
				value = value,
				onValueChange = onValueChange,
				placeholder = placeholder,
				shape = RoundedCornerShape(16.dp),
				colors = colors,
				modifier = modifier,
				leadingIcon = leadingIcon,
				trailingIcon = trailingIcon
		)
}

@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview() {
		var text by remember {
				mutableStateOf("")
		}
		EsisArchiveTheme {
				CustomTextField(
						modifier = Modifier.fillMaxWidth(),
						value = text,
						placeholder = {
								Text(text = "Prénom & Nom")
						},
						onValueChange = { text = it },
						trailingIcon = {
								Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
						}
				)
		}
}