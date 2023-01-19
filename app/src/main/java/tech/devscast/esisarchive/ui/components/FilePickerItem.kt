package tech.devscast.esisarchive.ui.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FileCopy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import tech.devscast.esisarchive.util.getFileNameAndSize

@Composable
fun FilePickerItem(modifier: Modifier = Modifier, fileUri: Uri?, onClick: () -> Unit) {
		val context = LocalContext.current

		var fileName: String? by remember {
				mutableStateOf(null)
		}

		var fileSize: String? by remember {
				mutableStateOf(null)
		}

		LaunchedEffect(fileUri) {
				if (fileUri != null) {
						val cursor = context.contentResolver.query(
								fileUri,
								null,
								null,
								null,
								null
						)
						fileName = cursor?.getFileNameAndSize()?.first
						fileSize = cursor?.getFileNameAndSize()?.second
						cursor?.close()
				} else {
						fileName = null
						fileSize = null
				}
		}

		Row(
				modifier = modifier
						.clickable(onClick = onClick)
						.fillMaxWidth()
						.clip(RoundedCornerShape(8.dp))
						.background(MaterialTheme.colors.secondary.copy(alpha = 0.3f))
						.padding(8.dp)
		) {
				Surface(
						shape = CircleShape,
						color = MaterialTheme.colors.secondary,
						modifier = Modifier.size(56.dp)
				) {
						Icon(
								imageVector = Icons.Rounded.FileCopy,
								contentDescription = null,
								modifier = Modifier
										.fillMaxSize()
										.padding(16.dp)
						)
				}

				Spacer(modifier = Modifier.width(16.dp))

				Column(
						modifier = Modifier.wrapContentHeight(),
						verticalArrangement = Arrangement.spacedBy(
								2.dp,
								alignment = Alignment.CenterVertically
						)
				) {
						Text(
								text = fileName ?: "Aucun fichier",
								style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.SemiBold)
						)

						Text(
								text = fileSize ?: "0Ko",
								style = MaterialTheme.typography.h4
						)
				}
		}
}