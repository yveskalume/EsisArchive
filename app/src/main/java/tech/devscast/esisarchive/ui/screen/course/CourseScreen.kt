package tech.devscast.esisarchive.ui.screen.course

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import tech.devscast.esisarchive.ui.components.CourseItem
import tech.devscast.esisarchive.ui.theme.EsisArchiveTheme

@Composable
fun CourseScreen(
		viewModel: CourseViewModel = viewModel()
) {
		val homeUiState by viewModel.courseUiState.collectAsState()

		val scaffoldState = rememberScaffoldState()


		Scaffold(
				scaffoldState = scaffoldState,
				topBar = {
						TopAppBar(
								backgroundColor = MaterialTheme.colors.surface,
								navigationIcon = {
										IconButton(onClick = { /*TODO*/ }) {
												Icon(imageVector = Icons.Rounded.ArrowBackIos, contentDescription = null)
										}
								},
								title = {
										Text(text = "Syllabus")
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
										is CourseUiState.Error -> {
												Text(text = state.message)
										}
										CourseUiState.Loading -> {
												CircularProgressIndicator()
										}
										is CourseUiState.Success -> {
												LazyVerticalGrid(
														contentPadding = PaddingValues(8.dp),
														verticalArrangement = Arrangement.spacedBy(8.dp),
														horizontalArrangement = Arrangement.spacedBy(8.dp),
														columns = GridCells.Fixed(2),
														content = {
																items(state.courses) { course ->
																		CourseItem(
																				course = course,
																				onClick = {},
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
}

@Preview
@Composable
fun CourseScreenPreview() {
		EsisArchiveTheme {
				CourseScreen()
		}
}