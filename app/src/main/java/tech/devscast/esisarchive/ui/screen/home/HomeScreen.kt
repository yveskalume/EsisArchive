package tech.devscast.esisarchive.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tech.devscast.esisarchive.data.entity.Promotion
import tech.devscast.esisarchive.ui.components.CategoryItem
import tech.devscast.esisarchive.ui.navigation.Route
import tech.devscast.esisarchive.ui.theme.EsisArchiveTheme
import tech.devscast.esisarchive.util.navigate

@Composable
fun HomeScreen(navController: NavController) {
		Scaffold(
				topBar = {
						TopAppBar(
								backgroundColor = Color.White,
								title = {
										Text(text = "My Esis")
								},
								actions = {
										IconButton(onClick = { navController.navigate(Route.Upload) }) {
												Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
										}
								}
						)
				},
		) { contentPadding ->
				LazyColumn(
						modifier = Modifier.padding(contentPadding),
						contentPadding = PaddingValues(16.dp),
						verticalArrangement = Arrangement.spacedBy(8.dp)
				) {
						item {
								Text(
										text = "Resources Academiques",
										style = MaterialTheme.typography.h3,
										fontWeight = FontWeight.SemiBold
								)
						}
						items(Promotion.values()) {
								CategoryItem(
										title = it.name,
										icon = Icons.Outlined.Folder,
										onClick = { navController.navigate("${Route.Course}/${it.name}") },
								)
						}

						item {
								Spacer(modifier = Modifier.height(8.dp))
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