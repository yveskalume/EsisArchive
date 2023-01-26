package tech.devscast.esisarchive.ui.screen.auth

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import tech.devscast.esisarchive.ui.components.CustomTextField
import tech.devscast.esisarchive.ui.navigation.Route
import tech.devscast.esisarchive.ui.screen.auth.logic.AuthUiState
import tech.devscast.esisarchive.ui.screen.auth.logic.AuthViewModel
import tech.devscast.esisarchive.ui.theme.EsisArchiveTheme
import tech.devscast.esisarchive.util.navigate

@Composable
fun SignInScreen(navController: NavController, viewModel: AuthViewModel = viewModel()) {

		val scrollState = rememberScrollState()

		val authUiState by viewModel.state.collectAsState()

		val context = LocalContext.current

		var email by remember { mutableStateOf("") }
		var password by remember { mutableStateOf("") }

		
		var isValid by remember { mutableStateOf(false) }
		
		LaunchedEffect(email,password) {
				isValid = email.isNotBlank() && password.isNotBlank() && password.length >= 8
		}

		var passwordIsHidden by remember { mutableStateOf(true) }

		LaunchedEffect(authUiState) {
				if (authUiState is AuthUiState.Success) {
						navController.navigate(Route.Home) {
								popUpTo(Route.Home.value)
						}
				} else if (authUiState is AuthUiState.Error) {
						Toast.makeText(
								context,
								(authUiState as AuthUiState.Error).message,
								Toast.LENGTH_LONG
						).show()
				}
		}

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


				CustomTextField(
						value = email,
						onValueChange = { email = it },
						placeholder = {
								Text(text = "Addresse email esis")
						},
						modifier = Modifier.fillMaxWidth()
				)

				CustomTextField(
						value = password,
						onValueChange = { password = it },
						visualTransformation = if (passwordIsHidden) {
								PasswordVisualTransformation()
						} else {
								VisualTransformation.None
						},
						trailingIcon = {
								if (passwordIsHidden) {
										IconButton(onClick = { passwordIsHidden = false }) {
												Icon(imageVector = Icons.Rounded.Visibility, contentDescription = null)
										}
								} else {
										IconButton(onClick = { passwordIsHidden = true }) {
												Icon(imageVector = Icons.Rounded.VisibilityOff, contentDescription = null)
										}
								}
						},
						placeholder = {
								Text(text = "Mot de passe")
						},
						modifier = Modifier
								.fillMaxWidth()
				)


				Button(
						enabled = isValid,
						onClick = {
								viewModel.signInWithEmailAndPassword(email, password)
						},
						modifier = Modifier
								.fillMaxWidth()
								.height(56.dp),
						shape = RoundedCornerShape(16.dp)
				) {
						Text(text = "Se connecter")
				}

				Row {
						Text(text = "Pas de compte ? ")
						Text(
								text = "S'inscrire",
								color = MaterialTheme.colors.primary,
								modifier = Modifier.clickable {
										navController.navigate(Route.SignUp)
								}
						)
				}
		}
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
		EsisArchiveTheme {
				SignInScreen(navController = rememberNavController())
		}
}