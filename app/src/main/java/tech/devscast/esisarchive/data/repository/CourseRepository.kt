package tech.devscast.esisarchive.data.repository

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import tech.devscast.esisarchive.data.entity.Course
import tech.devscast.esisarchive.data.util.Result

class CourseRepository {

		private val fireStore = FirebaseFirestore.getInstance()
		private val firebaseStorage = FirebaseStorage.getInstance()

		suspend fun create(course: Course,fileUri: Uri) {
				val url = uploadFile(course.uid,fileUri)
				val mCourse = course.copy(fileUrl = url)

				fireStore.document("courses/${course.uid}").set(mCourse).await()
		}

		private suspend fun uploadFile(uid: String,fileUri: Uri) : String {
				val uploadTask = firebaseStorage.getReference("syllabus").child(uid)
				uploadTask.putFile(fileUri).await()

				return uploadTask.downloadUrl.await().toString()

		}
		fun getAll() = callbackFlow {
				val courseCollection = fireStore.collection("courses")
				val subscription = courseCollection.addSnapshotListener { value, error ->
						if (error != null || value == null) {
								trySend(Result.Error(error?.message.toString()))
								return@addSnapshotListener
						}

						value.toObjects<Course>().also { data ->
								trySend(Result.Success(data))
						}
				}
				awaitClose { subscription.remove() }
		}
}