package tech.devscast.esisarchive.ui.screen.upload

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.Date
import java.util.UUID
import tech.devscast.esisarchive.data.entity.Course
import tech.devscast.esisarchive.data.entity.Promotion
import tech.devscast.esisarchive.ui.components.FilePickerItem
import tech.devscast.esisarchive.ui.components.PromotionChip
import tech.devscast.esisarchive.ui.screen.upload.logic.UploadCourseViewModel
import tech.devscast.esisarchive.ui.screen.upload.logic.UploadSideEffect

@Composable
fun UploadCourseScreen(viewModel: UploadCourseViewModel = viewModel()) {

		val uploadSideEffect by viewModel.courseUiEffect.collectAsState(initial = UploadSideEffect.Initial)

		val context = LocalContext.current

		LaunchedEffect(uploadSideEffect) {
				when (uploadSideEffect) {
						is UploadSideEffect.UploadFailure -> {
								Toast.makeText(
										context,
										"Echec : ${(uploadSideEffect as UploadSideEffect.UploadFailure).message}",
										Toast.LENGTH_SHORT
								).show()
						}
						UploadSideEffect.UploadSuccess -> {
								Toast.makeText(
										context,
										"Publié",
										Toast.LENGTH_SHORT
								).show()
						}
						else -> {

						}
				}
		}

		Scaffold(
				topBar = {
						TopAppBar(
								backgroundColor = MaterialTheme.colors.surface,
								navigationIcon = {
										IconButton(onClick = { /*TODO*/ }) {
												Icon(imageVector = Icons.Rounded.ArrowBackIos, contentDescription = null)
										}
								},
								title = {
										Text(text = "Nouveau syllabus")
								},
						)
				}
		) { contentPadding ->
				UploadScreenContent(
						onSubmit = { course, uri ->
								viewModel.uploadFile(course = course, fileUri = uri)
						},
						modifier = Modifier
								.fillMaxSize()
								.padding(contentPadding)
								.padding(16.dp)
				)
		}
}

@Composable
private fun UploadScreenContent(modifier: Modifier = Modifier, onSubmit: (Course, Uri) -> Unit) {


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

		LaunchedEffect(title, author, fileUri, selectedPromotion) {
				isValidForm =
						title.isNotBlank() && author.isNotBlank() && fileUri != null && selectedPromotion != null
		}

		val pdfIntentLauncher = rememberLauncherForActivityResult(
				ActivityResultContracts.GetContent()
		) { fileUri = it }

		Column(
				modifier = modifier,
				verticalArrangement = Arrangement.spacedBy(8.dp)
		) {

				FilePickerItem(
						fileUri = fileUri,
						onClick = { pdfIntentLauncher.launch("application/pdf") }
				)
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
						enabled = isValidForm,
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

								fileUri?.let { uri ->
										onSubmit(course, uri)
								}

						},
						modifier = Modifier
								.fillMaxWidth()
								.height(48.dp)
				) {
						Text(text = "Ajouter", style = MaterialTheme.typography.button)
				}
		}
}