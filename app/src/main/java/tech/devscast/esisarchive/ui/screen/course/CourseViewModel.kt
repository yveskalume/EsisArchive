package tech.devscast.esisarchive.ui.screen.course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import tech.devscast.esisarchive.data.repository.CourseRepository
import tech.devscast.esisarchive.data.util.Result

class CourseViewModel : ViewModel() {

		private val courseRepository = CourseRepository()

		private val _courseUiState: MutableStateFlow<CourseUiState> =
				MutableStateFlow(CourseUiState.Loading)
		val courseUiState: StateFlow<CourseUiState>
				get() = _courseUiState

		fun getCourses(promotion: String) {
				viewModelScope.launch {
						_courseUiState.emit(CourseUiState.Loading)
						courseRepository.getAlByPromotion(promotion).collect {
								when (it) {
										is Result.Error -> {
												_courseUiState.emit(CourseUiState.Failure(it.message))
										}
										is Result.Success -> {
												_courseUiState.emit(CourseUiState.Success(it.data))
										}
								}
						}

				}
		}

}