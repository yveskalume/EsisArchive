package tech.devscast.esisarchive.ui.screen.course

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Upload
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import tech.devscast.esisarchive.R
import tech.devscast.esisarchive.ui.components.CourseItem
import tech.devscast.esisarchive.ui.theme.EsisArchiveTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CourseScreen(
		viewModel: CourseViewModel = viewModel()
) {

		val homeUiState by viewModel.courseUiState.collectAsState()
		val homeUiEffect by viewModel.courseUiEffect.collectAsState(initial = CourseSideEffect.Initial)

		val coroutineScope = rememberCoroutineScope()

		val scaffoldState = rememberBottomSheetScaffoldState(
				bottomSheetState = BottomSheetState(initialValue = BottomSheetValue.Collapsed)
		)

		LaunchedEffect(homeUiEffect) {
				when (homeUiEffect) {
						is CourseSideEffect.UploadFailure -> {

						}
						CourseSideEffect.UploadSuccess -> {
								scaffoldState.bottomSheetState.collapse()
						}
						else -> {

						}
				}
		}



		BottomSheetScaffold(
				modifier = Modifier.fillMaxSize(),
				scaffoldState = scaffoldState,
				sheetShape = RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp),
				sheetPeekHeight = 0.dp,
				topBar = {
						TopAppBar(
								backgroundColor = MaterialTheme.colors.surface,
								navigationIcon = {
										Surface(
												shape = CircleShape, modifier = Modifier
														.size(56.dp)
														.padding(8.dp)
										) {
												Image(
														modifier = Modifier.fillMaxSize(),
														painter = painterResource(id = R.drawable.ic_launcher_background),
														contentDescription = null
												)
										}
								},
								title = {
										Text(text = "EsisShare")
								},
								actions = {
										Surface(shape = CircleShape) {
												IconButton(onClick = { /*TODO*/ }) {
														Icon(
																imageVector = Icons.Rounded.Search,
																contentDescription = null,
														)
												}
										}
								}
						)
				},
				floatingActionButton = {
						FloatingActionButton(onClick = {
								coroutineScope.launch {
										scaffoldState.bottomSheetState.expand()
								}
						}) {
								Icon(
										imageVector = Icons.Rounded.Upload,
										contentDescription = null,
										tint = Color.White
								)
						}
				},
				sheetContent = {
						CourseBottomSheet(
								modifier = Modifier
										.fillMaxWidth()
										.padding(16.dp),
								canSubmit = true,
								onSubmit = {

								}
						)
				}
		) {
				Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
																items(state.courses) {course ->
																		CourseItem(
																				course = course,
																				modifier = Modifier
																						.wrapContentHeight()
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