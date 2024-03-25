package com.example.hhplustddweek2.application.lesson

import com.example.hhplustddweek2.domain.LessonEnrollment
import com.example.hhplustddweek2.error.MaxEnrollCountException
import com.example.hhplustddweek2.service.LessonEnrollmentService
import com.example.hhplustddweek2.service.LessonService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component

@Component
@Transactional
class EnrollLessonApplication(
    private val lessonEnrollmentService: LessonEnrollmentService,
    private val lessonService: LessonService
) {
    fun run(lessonId: Long, userId: Long): LessonEnrollment {
        val lesson = lessonService.getLessonById(lessonId, withLock = true)
        if (lesson.enrollCount >= 30) {
            throw MaxEnrollCountException("Lesson has reached max enroll count")
        }

        return lessonEnrollmentService.enroll(lesson, userId)
    }
}