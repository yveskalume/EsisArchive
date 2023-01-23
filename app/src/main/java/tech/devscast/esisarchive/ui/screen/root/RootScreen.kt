package tech.devscast.esisarchive.ui.screen.root

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import tech.devscast.esisarchive.ui.navigation.BottomNavigationItems
import tech.devscast.esisarchive.ui.navigation.Route
import tech.devscast.esisarchive.ui.screen.auth.SignInScreen
import tech.devscast.esisarchive.ui.screen.auth.SignInUpScreen
import tech.devscast.esisarchive.ui.screen.course.CourseScreen
import tech.devscast.esisarchive.ui.screen.home.HomeScreen
import tech.devscast.esisarchive.ui.screen.upload.UploadCourseScreen
import tech.devscast.esisarchive.ui.theme.EsisArchiveTheme
import tech.devscast.esisarchive.util.composable

@Composable
fun RootScreen() {
		val navController = rememberNavController()

		Scaffold(
				bottomBar = {
						BottomNavigation(
								backgroundColor = Color.White
						) {
								val navBackStackEntry by navController.currentBackStackEntryAsState()
								val currentDestination = navBackStackEntry?.destination

								BottomNavigationItems.values().forEach { item ->
										BottomNavigationItem(
												selected = currentDestination?.hierarchy?.any { it.route == item.route.value } == true,
												onClick = {
														navController.navigate(item.route.value)
												},
												icon = { Icon(imageVector = item.icon, contentDescription = null) },
												label = { Text(text = item.label) }
										)
								}
						}
				}
		) { contentPadding ->
				NavHost(
						modifier = Modifier
								.padding(contentPadding)
								.safeContentPadding(),
						navController = navController,
						startDestination = Route.SignIn.value
				) {
						composable(route = Route.SignIn) {
								SignInScreen(navController = navController)
						}

						composable(route = Route.SignUp) {
								SignInUpScreen(navController = navController)
						}

						composable(route = Route.Home) {
								HomeScreen(navController = navController)
						}

						composable(route = Route.Course) {
								CourseScreen(navController = navController)
						}

						composable(route = Route.Upload) {
								UploadCourseScreen()
						}

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