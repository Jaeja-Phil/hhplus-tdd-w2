package com.example.hhplustddweek2.application.lesson

import com.example.hhplustddweek2.domain.Lesson
import com.example.hhplustddweek2.domain.LessonEnrollment
import com.example.hhplustddweek2.entity.LessonEnrollmentStatusType
import com.example.hhplustddweek2.error.MaxEnrollCountException
import com.example.hhplustddweek2.service.LessonEnrollmentService
import com.example.hhplustddweek2.service.LessonService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime

class EnrollLessonApplicationTest {
    private val lessonService: LessonService = mockk()
    private val lessonEnrollmentService: LessonEnrollmentService = mockk()
    private val enrollLessonApplication: EnrollLessonApplication = EnrollLessonApplication(
        lessonEnrollmentService, lessonService
    )

    @Test
    fun `run - lesson에 enrollCount가 30이상일 경우 MaxEnrollCountException을 raise 하는지`() {
        // given
        val lessonId = 1L
        val userId = 1L
        every { lessonService.getLessonById(lessonId, withLock = true) } returns
                Lesson(id = lessonId, name = "name", description = "description", lessonDate = LocalDateTime.now(), enrollCount = 30)

        // when & then
        assertThrows<MaxEnrollCountException> {
            enrollLessonApplication.run(lessonId, userId)
        }
    }

    @Test
    fun `run - 정상적으로 생성된 lessonEnrollment를 반환하는지`() {
        // given
        val lessonId = 1L
        val userId = 1L
        val lesson = Lesson(id = lessonId, name = "name", description = "description", lessonDate = LocalDateTime.now(), enrollCount = 29)
        every { lessonService.getLessonById(lessonId, withLock = true) } returns lesson
        val updatedLesson = lesson.copy(enrollCount = lesson.enrollCount + 1)
        every { lessonService.incrementEnrollCount(lesson) } returns updatedLesson
        every { lessonEnrollmentService.enroll(updatedLesson, userId) } returns
                LessonEnrollment(id = 1L, lesson = updatedLesson, userId = userId, status = LessonEnrollmentStatusType.ENROLL)

        // when & then
        val lessonEnrollment = enrollLessonApplication.run(lessonId, userId)
        assertEquals(1L, lessonEnrollment.id)
        assertEquals(lessonId, lessonEnrollment.lesson.id)
        assertEquals(lesson.enrollCount + 1, lessonEnrollment.lesson.enrollCount)
        assertEquals(userId, lessonEnrollment.userId)
        assertEquals(LessonEnrollmentStatusType.ENROLL, lessonEnrollment.status)
    }
}