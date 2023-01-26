package tech.devscast.esisarchive.data.entity

import java.util.Date

data class User(
		val uid: String = "",
		val name: String = "",
		val email: String = "",
		val profileUrl: String = "",
		val phoneNumber: String = "",
		val promotion: Promotion? = null,
		val createdAt: Date? = null,
		val updatedAt: Date? = null,
		val isAdmin: Boolean = false
)
