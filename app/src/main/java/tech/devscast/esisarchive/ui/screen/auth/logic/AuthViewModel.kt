package tech.devscast.esisarchive.ui.screen.auth.logic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import tech.devscast.esisarchive.data.entity.User
import tech.devscast.esisarchive.data.repository.UserRepository

class AuthViewModel : ViewModel() {
		private val userRepository : UserRepository = UserRepository()

		private val _state : MutableStateFlow<AuthUiState> = MutableStateFlow(AuthUiState.Initial)
		val state: StateFlow<AuthUiState>
			get() = _state



		fun signInWithEmailAndPassword(email: String,password: String) {
				viewModelScope.launch {
						_state.emit(AuthUiState.Loading)
						try {
								userRepository.signInWithEmailAndPassword(email, password)
								_state.emit(AuthUiState.Success)
						}catch (e: Exception) {
								_state.emit(AuthUiState.Error(e.message.toString()))
						}
				}
		}


		fun signUpWithEmailAndPassword(user: User,password: String) {
				viewModelScope.launch {
						_state.emit(AuthUiState.Loading)
						try {
								userRepository.signUpWithEmailAndPassword(user, password)
								_state.emit(AuthUiState.Success)
						}catch (e: Exception) {
								_state.emit(AuthUiState.Error(e.message.toString()))
						}
				}
		}
}