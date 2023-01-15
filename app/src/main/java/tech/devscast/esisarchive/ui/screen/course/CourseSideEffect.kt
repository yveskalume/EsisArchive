package tech.devscast.esisarchive.ui.screen.course

sealed interface CourseSideEffect {
		object Initial : CourseSideEffect
		object UploadingFile : CourseSideEffect
		object UploadSuccess : CourseSideEffect
		data class UploadFailure(val message: String) : CourseSideEffect
}