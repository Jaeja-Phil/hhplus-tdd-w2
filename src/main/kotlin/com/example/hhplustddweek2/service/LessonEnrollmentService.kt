package com.example.hhplustddweek2.service

import com.example.hhplustddweek2.domain.Lesson
import com.example.hhplustddweek2.domain.LessonEnrollment
import com.example.hhplustddweek2.error.ConflictException
import com.example.hhplustddweek2.error.NotFoundException
import com.example.hhplustddweek2.repository.lesson.LessonRepository
import com.example.hhplustddweek2.repository.lessonEnrollment.LessonEnrollmentRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class LessonEnrollmentService(
    private val lessonEnrollmentRepository: LessonEnrollmentRepository
) {
    @Transactional
    fun enroll(lesson: Lesson, userId: Long): LessonEnrollment {
        val existingLessonEnrollment = lessonEnrollmentRepository.findByUserIdAndLessonId(
            userId,
            lesson.id ?: throw NotFoundException("Lesson not found")
        )
        if (existingLessonEnrollment != null) {
            throw ConflictException("User already enrolled the lesson")
        }

        return lessonEnrollmentRepository.create(LessonEnrollment.newOf(lesson, userId))
    }

    fun checkEnrollment(lessonId: Long, userId: Long): LessonEnrollment {
        return lessonEnrollmentRepository.findByUserIdAndLessonId(userId, lessonId) ?: throw NotFoundException("Lesson not found")
    }
}