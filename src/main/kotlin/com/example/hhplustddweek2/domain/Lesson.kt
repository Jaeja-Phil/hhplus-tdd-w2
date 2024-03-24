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
    companion object {
        fun newOf(name: String, description: String, lessonDate: LocalDateTime): Lesson {
            return Lesson(
                id = null,
                name = name,
                description = description,
                lessonDate = lessonDate,
                enrollCount = 0
            )
        }
    }
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
