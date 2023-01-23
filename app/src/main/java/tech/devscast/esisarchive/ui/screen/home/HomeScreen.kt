package tech.devscast.esisarchive.ui.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tech.devscast.esisarchive.ui.components.CategoryItem
import tech.devscast.esisarchive.ui.components.CustomTextField
import tech.devscast.esisarchive.ui.components.WebResourceItem
import tech.devscast.esisarchive.ui.navigation.Route
import tech.devscast.esisarchive.ui.theme.EsisArchiveTheme
import tech.devscast.esisarchive.ui.theme.Gray002
import tech.devscast.esisarchive.util.navigate

@Composable
fun HomeScreen(navController: NavController) {
		Scaffold(
				topBar = {
						Row(
								modifier = Modifier
										.fillMaxWidth()
										.padding(vertical = 8.dp, horizontal = 16.dp),
								horizontalArrangement = Arrangement.SpaceBetween,
								verticalAlignment = Alignment.CenterVertically
						) {
								Column(
										modifier = Modifier.wrapContentHeight(),
										verticalArrangement = Arrangement.Center
								) {
										Text(
												text = "My Esis",
												style = MaterialTheme.typography.h2.copy(fontWeight = FontWeight.SemiBold)
										)
								}
								Surface(
										shape = CircleShape, modifier = Modifier
												.size(56.dp)
												.padding(8.dp),
										border = BorderStroke(1.dp, Color.Black)
								) {
										Image(
												modifier = Modifier
														.padding(4.dp)
														.fillMaxSize(),
												imageVector = Icons.Outlined.Person,
												contentDescription = null
										)
								}
						}
				}
		) { contentPadding ->
				LazyColumn(
						modifier = Modifier.padding(contentPadding),
						contentPadding = PaddingValues(16.dp),
						verticalArrangement = Arrangement.spacedBy(8.dp)
				) {
						item {
								CustomTextField(
										value = "",
										onValueChange = {},
										placeholder = {
												Text(text = "Recherche")
										},
										colors = TextFieldDefaults.outlinedTextFieldColors(
												backgroundColor = Gray002,
												unfocusedBorderColor = Gray002
										),
										modifier = Modifier.fillMaxWidth()
								)
						}
						item {
								Spacer(modifier = Modifier.height(8.dp))
						}
						item {
								Text(
										text = "Resources Academiques",
										style = MaterialTheme.typography.h3,
										fontWeight = FontWeight.SemiBold
								)
						}
						item {
								CategoryItem(
										title = "Syllabus",
										icon = Icons.Outlined.Folder,
										onClick = { navController.navigate(Route.Course) }
								)
						}

						item {
								Spacer(modifier = Modifier.height(8.dp))
						}

						item {
								Text(
										text = "En lignes",
										style = MaterialTheme.typography.h3,
										fontWeight = FontWeight.SemiBold
								)
						}

						items(5) {
								WebResourceItem(onClick = {})
						}
				}
		}
}

@Preview
@Composable
fun HomeScreenPreview() {
		EsisArchiveTheme {
				HomeScreen(navController = rememberNavController())
		}
}