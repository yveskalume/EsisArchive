package tech.devscast.esisarchive.ui.screen.root

import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tech.devscast.esisarchive.ui.navigation.Route
import tech.devscast.esisarchive.ui.screen.course.CourseScreen
import tech.devscast.esisarchive.ui.screen.home.HomeScreen
import tech.devscast.esisarchive.ui.screen.upload.UploadCourseScreen
import tech.devscast.esisarchive.ui.theme.EsisArchiveTheme

@Composable
fun RootScreen() {
		val navController = rememberNavController()
		NavHost(
				modifier = Modifier
						.safeContentPadding(),
				navController = navController,
				startDestination = Route.Home.value
		) {

				composable(route = Route.Home.value) {
						HomeScreen(navController = navController)
				}

				composable(route = "${Route.Course.value}/{promotion}") { backStackEntry ->
						val promotion = backStackEntry.arguments?.getString("promotion").toString()
						CourseScreen(navController = navController, promotion = promotion)
				}

				composable(route = Route.Upload.value) {
						UploadCourseScreen()
				}

		}
}

@Preview
@Composable
fun RootScreenPreview() {
		EsisArchiveTheme {
				RootScreen()
		}
}