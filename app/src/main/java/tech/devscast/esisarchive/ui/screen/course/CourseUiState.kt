package tech.devscast.esisarchive.ui.screen.course

import tech.devscast.esisarchive.data.entity.Course

sealed interface CourseUiState {
		object Loading : CourseUiState
		data class Failure(val message: String) : CourseUiState
		data class Success(val courses: List<Course>) : CourseUiState
}