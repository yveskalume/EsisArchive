package tech.devscast.esisarchive.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FileCopy
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Upload
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import tech.devscast.esisarchive.R
import tech.devscast.esisarchive.ui.theme.EsisArchiveTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

		val coroutineScope = rememberCoroutineScope()

		val scaffoldState = rememberBottomSheetScaffoldState(
				bottomSheetState = BottomSheetState(initialValue = BottomSheetValue.Collapsed)
		)

		BottomSheetScaffold(
				modifier = modifier,
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
						Column(
								modifier = Modifier
										.fillMaxWidth()
										.padding(16.dp),
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
										value = "",
										onValueChange = {},
										label = {
												Text(text = "Titre")
										},
								)
								OutlinedTextField(
										modifier = Modifier.fillMaxWidth(),
										value = "",
										onValueChange = {},
										label = {
												Text(text = "Auteur")
										}
								)
								Button(
										onClick = { /*TODO*/ }, modifier = Modifier
												.fillMaxWidth()
												.height(48.dp)
								) {
										Text(text = "Ajouter", style = MaterialTheme.typography.button)
								}
						}
				}
		) {
				LazyVerticalGrid(
						contentPadding = PaddingValues(8.dp),
						verticalArrangement = Arrangement.spacedBy(8.dp),
						horizontalArrangement = Arrangement.spacedBy(8.dp),
						columns = GridCells.Fixed(2),
						content = {
								items(4) {
										Column(
												modifier = Modifier
														.wrapContentHeight()
														.fillMaxWidth()
										) {
												Surface(
														shape = RoundedCornerShape(8.dp),
														modifier = Modifier
																.height(200.dp)
																.fillMaxWidth()
												) {
														Image(
																painter = painterResource(id = R.drawable.ic_launcher_background),
																contentScale = ContentScale.Crop,
																contentDescription = null
														)
												}

												Text(
														text = "Lorem ipsum",
														style = MaterialTheme.typography.h4,
														color = Color.Black,
														modifier = Modifier.fillMaxWidth()
												)

												Text(
														text = "Musumbu kaninda",
														style = MaterialTheme.typography.caption,
														modifier = Modifier.fillMaxWidth()
												)
										}
								}
						}
				)
		}
}

@Preview
@Composable
fun HomeScreenPreview() {
		EsisArchiveTheme {
				HomeScreen()
		}
}