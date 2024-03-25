package com.example.hhplustddweek2.repository.lessonEnrollment

import com.example.hhplustddweek2.domain.LessonEnrollment
import com.example.hhplustddweek2.entity.LessonEnrollmentEntity
import com.example.hhplustddweek2.entity.LessonEnrollmentStatusType
import org.springframework.stereotype.Repository

@Repository
class LessonEnrollmentRepository(
    private val lessonEnrollmentJpaRepository: LessonEnrollmentJpaRepository
) {
    fun findAll(): List<LessonEnrollment> {
        return lessonEnrollmentJpaRepository.findAll().map { it.toDomain() }
    }

    fun findById(id: Long): LessonEnrollment? {
        return lessonEnrollmentJpaRepository.findById(id).orElse(null)?.toDomain()
    }

    fun create(lessonEnrollment: LessonEnrollment): LessonEnrollment {
        return lessonEnrollmentJpaRepository.save(
            LessonEnrollmentEntity(
                id = null,
                lesson = lessonEnrollment.lesson.toEntity(),
                userId = lessonEnrollment.userId,
                status = LessonEnrollmentStatusType.ENROLL
            )
        ).toDomain()
    }
}