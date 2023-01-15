package tech.devscast.esisarchive.ui.screen.course

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import tech.devscast.esisarchive.data.entity.Course
import tech.devscast.esisarchive.data.repository.CourseRepository
import tech.devscast.esisarchive.data.util.Result

class CourseViewModel : ViewModel() {

		private val courseRepository = CourseRepository()

		private val effectChannel: Channel<CourseSideEffect> = Channel(Channel.CONFLATED)
		val courseUiEffect: Flow<CourseSideEffect>
				get() = effectChannel.receiveAsFlow()


		val courseUiState : StateFlow<CourseUiState> = courseRepository.getAll().map { result ->
				when(result) {
						is Result.Error -> CourseUiState.Error(result.message)
						is Result.Success -> CourseUiState.Success(result.data)
				}
		}.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(5000),
				initialValue = CourseUiState.Loading
		)

		fun uploadFile(course: Course) {
				viewModelScope.launch {
						effectChannel.trySend(CourseSideEffect.UploadingFile)
						try {
								courseRepository.create(course)
								effectChannel.trySend(CourseSideEffect.UploadSuccess)
						} catch (e: Exception) {
								effectChannel.trySend(CourseSideEffect.UploadFailure(e.message.toString()))
						}

				}
		}

}