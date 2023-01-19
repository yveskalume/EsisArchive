package tech.devscast.esisarchive.ui.screen.upload.logic

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import tech.devscast.esisarchive.data.entity.Course
import tech.devscast.esisarchive.data.repository.CourseRepository

class UploadCourseViewModel : ViewModel() {

		private val courseRepository = CourseRepository()

		private val effectChannel: Channel<UploadSideEffect> = Channel(Channel.CONFLATED)
		val courseUiEffect: Flow<UploadSideEffect>
				get() = effectChannel.receiveAsFlow()

		fun uploadFile(course: Course,fileUri: Uri) {
				viewModelScope.launch {
						effectChannel.trySend(UploadSideEffect.UploadingFile)
						try {
								courseRepository.create(course,fileUri)
								effectChannel.trySend(UploadSideEffect.UploadSuccess)
						} catch (e: Exception) {
								effectChannel.trySend(UploadSideEffect.UploadFailure(e.message.toString()))
						}

				}
		}
}