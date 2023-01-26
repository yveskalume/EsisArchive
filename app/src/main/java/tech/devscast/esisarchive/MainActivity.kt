package tech.devscast.esisarchive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import tech.devscast.esisarchive.ui.screen.root.RootScreen
import tech.devscast.esisarchive.ui.theme.EsisArchiveTheme

class MainActivity : ComponentActivity() {
		override fun onCreate(savedInstanceState: Bundle?) {
				super.onCreate(savedInstanceState)
				setContent {
						EsisArchiveTheme {
								// A surface container using the 'background' color from the theme
								Surface(
										modifier = Modifier.fillMaxSize(),
										color = MaterialTheme.colors.background
								) {
										RootScreen()
								}
						}
				}
		}
}