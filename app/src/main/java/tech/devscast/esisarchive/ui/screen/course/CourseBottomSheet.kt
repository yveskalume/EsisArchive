package tech.devscast.esisarchive.ui.screen.course

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FileCopy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.util.Date
import java.util.UUID
import tech.devscast.esisarchive.data.entity.Course
import tech.devscast.esisarchive.data.entity.Promotion
import tech.devscast.esisarchive.ui.components.PromotionChip

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun CourseBottomSheet(
		modifier: Modifier = Modifier,
		canSubmit: Boolean,
		onSubmit: (Course) -> Unit
) {
		var title by remember {
				mutableStateOf("")
		}

		var author by remember {
				mutableStateOf("")
		}

		var selectedPromotion: Promotion? by remember {
				mutableStateOf(null)
		}

		var fileUri: Uri? by remember {
				mutableStateOf(null)
		}

		var isValidForm by remember {
				mutableStateOf(false)
		}

		LaunchedEffect(title, author, fileUri) {
				isValidForm =
						title.isNotBlank() && author.isNotBlank() && fileUri != null && selectedPromotion != null
		}
		Column(
				modifier = modifier,
				verticalArrangement = Arrangement.spacedBy(8.dp)
		) {

				Row(
						modifier = Modifier
								.clickable { }
								.fillMaxWidth()
								.clip(RoundedCornerShape(8.dp))
								.background(Color.Cyan.copy(alpha = 0.3f))
								.padding(8.dp)
				) {
						Surface(
								shape = CircleShape,
								color = Color.Cyan,
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
										text = "Lorem ipsum",
										style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.SemiBold)
								)

								Text(
										text = "1MB",
										style = MaterialTheme.typography.h4
								)
						}
				}
				OutlinedTextField(
						modifier = Modifier.fillMaxWidth(),
						value = title,
						onValueChange = {
								title = it
						},
						label = {
								Text(text = "Titre")
						},
				)
				OutlinedTextField(
						modifier = Modifier.fillMaxWidth(),
						value = author,
						onValueChange = {
								author = it
						},
						label = {
								Text(text = "Auteur")
						}
				)

				Text(text = "Promotion")
				Row(modifier = Modifier.fillMaxWidth()) {
						Promotion.values().forEach { promotion ->
								key(promotion.name) {
										PromotionChip(
												onClick = {
														selectedPromotion = promotion
												},
												isChecked = selectedPromotion == promotion,
												promotion = promotion
										)
								}
						}
				}

				Button(
						enabled = canSubmit && isValidForm,
						onClick = {
								val course = Course(
										uid = UUID.randomUUID().toString(),
										title = title,
										author = author,
										downloads = 0,
										userUid = "",
										createdAt = Date(),
										updatedAt = Date(),
										promotion = selectedPromotion!!,
										tags = emptyList(),
										validated = false

								)
						},
						modifier = Modifier
								.fillMaxWidth()
								.height(48.dp)
				) {
						Text(text = "Ajouter", style = MaterialTheme.typography.button)
				}
		}
}