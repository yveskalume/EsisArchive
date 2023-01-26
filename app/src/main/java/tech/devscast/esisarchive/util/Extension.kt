package tech.devscast.esisarchive.util

import android.database.Cursor
import android.provider.OpenableColumns
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.composable
import androidx.navigation.get
import tech.devscast.esisarchive.ui.navigation.Route

fun NavController.navigate(route: Route,builder: (NavOptionsBuilder.() -> Unit)? = null) {
		if (builder != null) {
				navigate(route.value,builder)
		} else {
				navigate(route.value)
		}
}

fun NavGraphBuilder.composable(
		route: Route,
		arguments: List<NamedNavArgument> = emptyList(),
		deepLinks: List<NavDeepLink> = emptyList(),
		content: @Composable (NavBackStackEntry) -> Unit
) {
		composable(route.value, arguments, deepLinks, content)
}

fun Cursor.getFileNameAndSize(): Pair<String, String> {
		return try {
				moveToFirst()
				val nameIndex = getColumnIndex(OpenableColumns.DISPLAY_NAME)
				val sizeIndex = getColumnIndex(OpenableColumns.SIZE)
				val name = getString(nameIndex)
				val size = getLong(sizeIndex).formatedFileSize()
				Pair(name, size)
		} catch (e: Exception) {
				Pair("Erreur", "Erreur")
		}
}

fun Long.formatedFileSize(): String {
		return if ( this >= (1024 * 1024)) {
				"${ this / (1024 * 1024)} MB"
		} else {
				"${ this / 1024} KB"
		}
}