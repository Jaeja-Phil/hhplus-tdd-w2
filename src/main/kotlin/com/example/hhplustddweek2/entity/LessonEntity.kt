package com.example.hhplustddweek2.entity

import com.example.hhplustddweek2.domain.Lesson
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "lessons")
data class LessonEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // since we are using postgres, it will use the sequence
    val id: Long?,
    val name: String,
    val description: String,
    val lessonDate: LocalDateTime,
    val enrollCount: Int = 0
) {
    fun toDomain() = Lesson(
        id = id,
        name = name,
        description = description,
        lessonDate = lessonDate,
        enrollCount = enrollCount
    )
}
