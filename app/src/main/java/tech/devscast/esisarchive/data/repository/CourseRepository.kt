package tech.devscast.esisarchive.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import tech.devscast.esisarchive.data.entity.Course
import tech.devscast.esisarchive.data.util.Result

class CourseRepository {

		private val fireStore = FirebaseFirestore.getInstance()

		suspend fun create(course: Course) {
				fireStore.document("courses/${course.uid}").set(course).await()
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