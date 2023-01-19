package tech.devscast.esisarchive.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.UploadFile
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavigationItems(val label: String, val icon: ImageVector, val route: Route) {
		Home("Accueil", Icons.Rounded.Home, Route.Home),
		Upload("Publier", Icons.Rounded.UploadFile, Route.Upload)
}