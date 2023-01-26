package tech.devscast.esisarchive.ui.screen.course

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tech.devscast.esisarchive.data.entity.Course
import tech.devscast.esisarchive.ui.components.CourseItem
import tech.devscast.esisarchive.ui.theme.EsisArchiveTheme
import tech.devscast.esisarchive.util.downloadFile

@Composable
fun CourseScreen(
		navController: NavController,
		promotion: String,
		viewModel: CourseViewModel = viewModel()
) {
		val homeUiState by viewModel.courseUiState.collectAsState()

		val context = LocalContext.current

		var selectedCourse: Course? by remember {
				mutableStateOf(null)
		}

		val scaffoldState = rememberScaffoldState()

		LaunchedEffect(promotion) {
				viewModel.getCourses(promotion)
		}


		Scaffold(
				scaffoldState = scaffoldState,
				topBar = {
						TopAppBar(
								backgroundColor = MaterialTheme.colors.surface,
								navigationIcon = {
										IconButton(onClick = { navController.navigateUp() }) {
												Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
										}
								},
								title = {
										Text(text = promotion)
								},
						)
				},

				) { contentPadding ->
				Box(
						modifier = Modifier
								.padding(contentPadding)
								.fillMaxSize(), contentAlignment = Alignment.Center
				) {
						Crossfade(targetState = homeUiState) { state ->
								when (state) {
										is CourseUiState.Failure -> {
												Text(text = state.message)
										}
										CourseUiState.Loading -> {
												Box(
														modifier = Modifier.fillMaxSize(),
														contentAlignment = Alignment.Center
												) {
														CircularProgressIndicator()
												}
										}
										is CourseUiState.Success -> {
												LazyColumn(
														modifier = Modifier.fillMaxSize(),
														contentPadding = PaddingValues(16.dp),
														verticalArrangement = Arrangement.spacedBy(8.dp),
														content = {
																items(state.courses) { course ->
																		CourseItem(
																				course = course,
																				onClick = {
																						selectedCourse = course
																				},
																				modifier = Modifier
																						.fillMaxWidth()
																		)
																}
														}
												)
										}
								}
						}
				}
		}


		if (selectedCourse != null) {
				AlertDialog(
						onDismissRequest = { selectedCourse = null },
						text = {
								Text(buildAnnotatedString {
										append("Voulez vous télécharger")
										withStyle(TextStyle(fontWeight = FontWeight.Bold).toSpanStyle()) {
												append(selectedCourse?.title.toString())
										}
										append(" de ")
										withStyle(TextStyle(fontWeight = FontWeight.Bold).toSpanStyle()) {
												append(selectedCourse?.author.toString())
										}
										append(" ?")
								})
						},
						buttons = {
								Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
										TextButton(
												onClick = {
														val path = "${selectedCourse?.title},${selectedCourse?.author}.pdf"
														context.downloadFile(selectedCourse!!.fileUrl,path)
														selectedCourse = null
												}
										) {
												Text(text = "Oui (${selectedCourse?.fileSize})")
										}
										Spacer(modifier = Modifier.width(8.dp))
										TextButton(onClick = { selectedCourse = null }) {
												Text(text = "Non")
										}
								}
						},
				)
		}
}

@Preview
@Composable
fun CourseScreenPreview() {
		EsisArchiveTheme {
				CourseScreen(navController = rememberNavController(), promotion = "L1")
		}
}