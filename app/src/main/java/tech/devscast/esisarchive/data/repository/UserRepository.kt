package tech.devscast.esisarchive.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import tech.devscast.esisarchive.data.entity.User

class UserRepository {
		private val fireStore = FirebaseFirestore.getInstance()
		private val firebaseStorage = FirebaseStorage.getInstance()
		private val auth = FirebaseAuth.getInstance()


		suspend fun signInWithEmailAndPassword(email: String, password: String) {
				auth.signInWithEmailAndPassword(email, password).await()
		}


		suspend fun signUpWithEmailAndPassword(user: User, password: String) {
				val firebaseUser = auth.createUserWithEmailAndPassword(user.email, password).await().user
				val newUser = user.copy(uid = firebaseUser?.uid!!)
				saveToFirestore(newUser)
		}

		private suspend fun saveToFirestore(user: User) {
				fireStore.document("users/${user.uid}").set(user).await()
		}
}