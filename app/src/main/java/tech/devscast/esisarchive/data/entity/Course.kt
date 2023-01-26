package tech.devscast.esisarchive.data.entity

import java.util.Date

data class Course(
		val uid: String = "",
		val title: String = "",
		val author: String = "",
		val fileUrl: String = "",
		val downloads: Int = 0,
		val fileSize: String = "",
		val userUid: String = "",
		val createdAt: Date? = null,
		val updatedAt: Date? = null,
		val promotion: Promotion = Promotion.L1,
		val tags: List<Tag> = emptyList(),
		val validated: Boolean = false
)
