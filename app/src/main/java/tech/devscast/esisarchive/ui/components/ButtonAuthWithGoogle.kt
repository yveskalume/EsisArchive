package tech.devscast.esisarchive.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Facebook
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.base.R
import tech.devscast.esisarchive.ui.theme.EsisArchiveTheme

@Composable
fun ButtonAuthGoogle(
		modifier: Modifier = Modifier,
		enabled: Boolean = true,
		onClick: () -> Unit
) {
		OutlinedButton(
				enabled = enabled,
				border = ButtonDefaults.outlinedBorder.copy(width = 1.dp),
				modifier = modifier
						.fillMaxWidth()
						.height(56.dp),
				onClick = onClick,
				content = {
						Row(
								modifier = Modifier.fillMaxWidth(),
								horizontalArrangement = Arrangement.SpaceBetween,
								verticalAlignment = Alignment.CenterVertically,
								content = {
										Icon(
												tint = Color.Unspecified,
												painter = painterResource(
														id = R.drawable.googleg_standard_color_18
												),
												contentDescription = null,
										)
										Text(
												fontSize = 16.sp,
												color = MaterialTheme.colors.onSurface,
												text = "Se connecter avec Google"
										)
										Icon(
												tint = Color.Transparent,
												imageVector = Icons.Rounded.Facebook,
												contentDescription = null,
										)
								}
						)
				}
		)
}

@Preview
@Composable
fun ButtonAuthGooglePreview() {
		EsisArchiveTheme {
				ButtonAuthGoogle {

				}
		}
}