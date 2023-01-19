package tech.devscast.esisarchive.ui.screen.upload.logic

sealed interface UploadSideEffect {
		object Initial : UploadSideEffect
		object UploadingFile : UploadSideEffect
		object UploadSuccess : UploadSideEffect
		data class UploadFailure(val message: String) : UploadSideEffect
}