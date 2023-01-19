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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tech.devscast.esisarchive.R
import tech.devscast.esisarchive.ui.components.CategoryItem
import tech.devscast.esisarchive.ui.navigation.Route
import tech.devscast.esisarchive.ui.theme.EsisArchiveTheme
import tech.devscast.esisarchive.ui.theme.Gray002
import tech.devscast.esisarchive.ui.theme.Green200
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
								Column(modifier = Modifier.wrapContentHeight()) {
										Text(
												text = "My Esis",
												style = MaterialTheme.typography.h2.copy(fontWeight = FontWeight.SemiBold)
										)
										Text(text = "Salut Yves")
								}
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
						}
				}
		) { contentPadding ->
				LazyColumn(
						modifier = Modifier.padding(contentPadding),
						contentPadding = PaddingValues(16.dp),
						verticalArrangement = Arrangement.spacedBy(8.dp)
				) {
						item {
								Text(
										text = "Catégories",
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