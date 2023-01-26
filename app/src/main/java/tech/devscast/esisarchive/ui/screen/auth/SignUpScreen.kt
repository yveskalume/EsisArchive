package tech.devscast.esisarchive.ui.screen.auth

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import tech.devscast.esisarchive.R
import tech.devscast.esisarchive.ui.components.CustomTextField
import tech.devscast.esisarchive.ui.navigation.Route
import tech.devscast.esisarchive.ui.theme.EsisArchiveTheme
import tech.devscast.esisarchive.util.navigate

@Composable
fun SignInUpScreen(navController: NavController) {
		val scrollState = rememberScrollState()

		var profile: Uri? by remember {
				mutableStateOf(null)
		}

		val intentLauncher = rememberLauncherForActivityResult(
				contract = ActivityResultContracts.GetContent(),
				onResult = { profile = it }
		)

		Column(
				modifier = Modifier
						.fillMaxSize()
						.padding(16.dp)
						.verticalScroll(scrollState),
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.spacedBy(
						space = 24.dp,
						alignment = Alignment.CenterVertically
				)
		) {

				AsyncImage(
						model = ImageRequest.Builder(LocalContext.current)
								.data(profile)
								.crossfade(true)
								.build(),
						placeholder = painterResource(R.drawable.user_placeholder),
						contentDescription = null,
						contentScale = ContentScale.Crop,
						modifier = Modifier
								.clip(CircleShape)
								.size(140.dp)
								.clickable { intentLauncher.launch("image/*") }
				)

				CustomTextField(
						value = "",
						onValueChange = {},
						placeholder = {
								Text(text = "Prénom & Nom")
						},
						modifier = Modifier.fillMaxWidth()
				)

				CustomTextField(
						value = "",
						onValueChange = {},
						placeholder = {
								Text(text = "Addresse email esis")
						},
						modifier = Modifier.fillMaxWidth()
				)

				CustomTextField(
						value = "",
						onValueChange = {},
						placeholder = {
								Text(text = "Mot de passe")
						},
						modifier = Modifier
								.fillMaxWidth()
				)

				CustomTextField(
						value = "",
						onValueChange = {},
						placeholder = {
								Text(text = "Confirmer mot de passe")
						},
						modifier = Modifier.fillMaxWidth()
				)

				Spacer(modifier = Modifier.height(1.dp))

				Button(
						onClick = { navController.navigate(Route.Home) },
						modifier = Modifier
								.fillMaxWidth()
								.height(56.dp),
						shape = RoundedCornerShape(16.dp)
				) {
						Text(text = "Créer le compte")
				}

				Row {
						Text(text = "Vous avez déjà un compte ? ")
						Text(
								text = "Connexion",
								color = MaterialTheme.colors.primary,
								modifier = Modifier.clickable {
										navController.navigate(Route.SignIn)
								}
						)
				}
		}
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
		EsisArchiveTheme {
				SignInUpScreen(navController = rememberNavController())
		}
}