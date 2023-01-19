package tech.devscast.esisarchive.util

import android.database.Cursor
import android.provider.OpenableColumns
import androidx.navigation.NavController
import tech.devscast.esisarchive.ui.navigation.Route

fun NavController.navigate(route: Route) {
		navigate(route.value)
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