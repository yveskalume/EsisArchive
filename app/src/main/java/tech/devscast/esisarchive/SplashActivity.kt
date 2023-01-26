package tech.devscast.esisarchive

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import tech.devscast.esisarchive.ui.theme.EsisArchiveTheme

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
		override fun onCreate(savedInstanceState: Bundle?) {
				super.onCreate(savedInstanceState)
				setContent {
						EsisArchiveTheme {
								Surface(color = MaterialTheme.colors.background) {
										SplashScreen(
												next = {
														navigateNext()
												}
										)
								}
						}
				}
		}

		private fun navigateNext() {
				val intent = Intent(this, MainActivity::class.java)
				startActivity(intent)
				finish()
		}
}

@Preview
@Composable
private fun SplashScreen(next: () -> Unit = {}) {

		val scale = remember {
				Animatable(0f)
		}

		LaunchedEffect(Unit) {
				scale.animateTo(
						targetValue = 1f,
						animationSpec = tween(
								durationMillis = 900,
								easing = {
										OvershootInterpolator(5f).getInterpolation(it)
								})
				)
				delay(500L)
				next()
		}

		Box(modifier = Modifier.fillMaxSize()) {
				Image(
						painter = painterResource(id = R.drawable.ic_launcher_background),
						contentDescription = null,
						modifier = Modifier
								.align(Alignment.Center)
								.size(120.dp)
								.scale(scale.value),
						contentScale = ContentScale.Fit
				)

				Image(
						painter = painterResource(id = R.drawable.devscast),
						contentDescription = null,
						modifier = Modifier
								.align(Alignment.BottomCenter)
								.height(120.dp)
								.width(140.dp),
						contentScale = ContentScale.Inside
				)
		}
}