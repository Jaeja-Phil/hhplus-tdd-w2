package com.example.hhplustddweek2.service

import com.example.hhplustddweek2.domain.Lesson
import com.example.hhplustddweek2.domain.LessonEnrollment
import com.example.hhplustddweek2.entity.LessonEnrollmentStatusType
import com.example.hhplustddweek2.error.ConflictException
import com.example.hhplustddweek2.error.NotFoundException
import com.example.hhplustddweek2.repository.lessonEnrollment.LessonEnrollmentRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime

class LessonEnrollmentServiceTest {
    private val lessonEnrollmentRepository: LessonEnrollmentRepository = mockk()
    private val lessonEnrollmentService = LessonEnrollmentService(lessonEnrollmentRepository)

    @Test
    fun `enroll - id가 없는 lesson일 경우 NotFoundException이 발생한다`() {
        // given
        val lesson = Lesson.newOf("name", "description", LocalDateTime.now())
        val userId = 1L

        // when & then
        val exception = assertThrows<NotFoundException> {
            lessonEnrollmentService.enroll(lesson, userId)
        }
        assertEquals("Lesson not found", exception.message)
    }

    @Test
    fun `enroll - 이미 수강신청한 lesson일 경우 ConflictException이 발생한다`() {
        // given
        val userId = 1L
        val lessonId = 1L
        val lesson = Lesson(id = lessonId, name = "name", description = "description", lessonDate = LocalDateTime.now(), enrollCount = 0)
        every { lessonEnrollmentRepository.findByUserIdAndLessonId(userId, lessonId) } throws
            ConflictException("User already enrolled the lesson")

        // when & then
        val exception = assertThrows<ConflictException> {
            lessonEnrollmentService.enroll(lesson, 1L)
        }
        assertEquals("User already enrolled the lesson", exception.message)
    }

    @Test
    fun `enroll - 정상적으로 수강신청이 되는지`() {
        // given
        val userId = 1L
        val lessonId = 1L
        val lesson = Lesson(id = lessonId, name = "name", description = "description", lessonDate = LocalDateTime.now(), enrollCount = 0)
        every { lessonEnrollmentRepository.findByUserIdAndLessonId(userId, lessonId) } returns null
        every { lessonEnrollmentRepository.create(any()) } returns
                LessonEnrollment(id = 1L, lesson = lesson, userId = userId, status = LessonEnrollmentStatusType.ENROLL)

        // when
        val lessonEnrollment = lessonEnrollmentService.enroll(lesson, userId)

        // then
        assertEquals(lessonId, lessonEnrollment.lesson.id)
        assertEquals(userId, lessonEnrollment.userId)
        assertEquals(LessonEnrollmentStatusType.ENROLL, lessonEnrollment.status)
    }
}