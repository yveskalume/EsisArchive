package tech.devscast.esisarchive.ui.screen.course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import tech.devscast.esisarchive.data.repository.CourseRepository
import tech.devscast.esisarchive.data.util.Result

class CourseViewModel : ViewModel() {

		private val courseRepository = CourseRepository()


		val courseUiState: StateFlow<CourseUiState> = courseRepository.getAll().map { result ->
				when (result) {
						is Result.Error -> CourseUiState.Error(result.message)
						is Result.Success -> CourseUiState.Success(result.data)
				}
		}.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(5000),
				initialValue = CourseUiState.Loading
		)
}