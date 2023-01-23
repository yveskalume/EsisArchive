package tech.devscast.esisarchive.ui.screen.auth

import androidx.compose.foundation.Image
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
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tech.devscast.esisarchive.R
import tech.devscast.esisarchive.ui.components.CustomTextField
import tech.devscast.esisarchive.ui.navigation.Route
import tech.devscast.esisarchive.ui.theme.EsisArchiveTheme
import tech.devscast.esisarchive.util.navigate

@Composable
fun SignInUpScreen(navController: NavController) {
		val scrollState = rememberScrollState()
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
				Surface(modifier = Modifier.size(140.dp), shape = CircleShape) {
						Image(
								painter = painterResource(id = R.drawable.user_placeholder),
								contentDescription = null
						)
				}

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