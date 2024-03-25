package com.example.hhplustddweek2.entity

import com.example.hhplustddweek2.domain.LessonEnrollment
import jakarta.persistence.*

enum class LessonEnrollmentStatusType {
    ENROLL, WITHDRAW
}

@Entity
@Table(name = "lesson_enrollments")
data class LessonEnrollmentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @ManyToOne
    @JoinColumn(name = "lesson_id")
    val lesson: LessonEntity,
    val userId: Long,
    val status: LessonEnrollmentStatusType = LessonEnrollmentStatusType.ENROLL
) {
    fun toDomain() = LessonEnrollment(
        id = id,
        lesson = lesson.toDomain(),
        userId = userId,
        status = status
    )
}
