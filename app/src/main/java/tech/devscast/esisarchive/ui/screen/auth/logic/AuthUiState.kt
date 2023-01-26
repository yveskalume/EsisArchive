package tech.devscast.esisarchive.ui.screen.auth.logic

sealed interface AuthUiState {
		object Initial : AuthUiState
		object Loading : AuthUiState
		object Success : AuthUiState
		data class Error(val message: String) : AuthUiState
}