package com.example.hhplustddweek2.domain

import com.example.hhplustddweek2.entity.LessonEnrollmentEntity
import com.example.hhplustddweek2.entity.LessonEnrollmentStatusType

data class LessonEnrollment(
    val id: Long?,
    val lesson: Lesson,
    val userId: Long,
    val status: LessonEnrollmentStatusType
) {
    fun toEntity(): LessonEnrollmentEntity {
        return LessonEnrollmentEntity(
            id = id,
            lesson = lesson.toEntity(),
            userId = userId,
            status = status
        )
    }

    companion object {
        fun newOf(lesson: Lesson, userId: Long): LessonEnrollment {
            return LessonEnrollment(
                id = null,
                lesson = lesson,
                userId = userId,
                status = LessonEnrollmentStatusType.ENROLL
            )
        }
    }
}
