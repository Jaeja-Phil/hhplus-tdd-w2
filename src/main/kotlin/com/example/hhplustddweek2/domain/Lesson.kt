package com.example.hhplustddweek2.domain

import com.example.hhplustddweek2.entity.LessonEntity
import java.time.LocalDateTime

data class Lesson(
    val id: Long?,
    val name: String,
    val description: String,
    val lessonDate: LocalDateTime,
    val enrollCount: Int
) {
    fun toEntity(): LessonEntity {
        return LessonEntity(
            id = id,
            name = name,
            description = description,
            lessonDate = lessonDate,
            enrollCount = enrollCount
        )
    }
}
