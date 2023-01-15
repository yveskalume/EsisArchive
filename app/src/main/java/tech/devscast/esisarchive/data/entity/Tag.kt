package tech.devscast.esisarchive.data.entity

import java.util.Date

data class Tag(
		val uid: String = "",
		val value: String = "",
		val createdAt: Date? = null,
		val updatedAt: Date? = null,
)