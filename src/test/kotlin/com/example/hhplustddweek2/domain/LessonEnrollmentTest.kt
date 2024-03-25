package com.example.hhplustddweek2.domain

import com.example.hhplustddweek2.entity.LessonEnrollmentStatusType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class LessonEnrollmentTest {
    @Test
    fun `toEntity - entity로 정상적으로 변환하는지`() {
        // given
        val lesson = Lesson(
            id = 1L,
            name = "name",
            description = "description",
            lessonDate = LocalDateTime.now(),
            enrollCount = 0
        )
        val lessonEnrollment = LessonEnrollment(
            id = 1L,
            lesson = lesson,
            userId = 1L,
            status = LessonEnrollmentStatusType.ENROLL
        )

        // when
        val lessonEnrollmentEntity = lessonEnrollment.toEntity()

        // then
        assertEquals(lessonEnrollment.id, lessonEnrollmentEntity.id)
        assertEquals(lessonEnrollment.lesson.toEntity(), lessonEnrollmentEntity.lesson)
        assertEquals(lessonEnrollment.userId, lessonEnrollmentEntity.userId)
        assertEquals(lessonEnrollment.status, lessonEnrollmentEntity.status)
    }

    @Test
    fun `newOf - lesson과 userId를 받아 id가 null인 LessonEnrollment을 생성하는지`() {
        // given
        val lesson = Lesson(
            id = 1L,
            name = "name",
            description = "description",
            lessonDate = LocalDateTime.now(),
            enrollCount = 0
        )
        val userId = 1L

        // when
        val lessonEnrollment = LessonEnrollment.newOf(lesson, userId)

        // then
        assertNull(lessonEnrollment.id)
        assertEquals(lesson, lessonEnrollment.lesson)
        assertEquals(userId, lessonEnrollment.userId)
        assertEquals(LessonEnrollmentStatusType.ENROLL, lessonEnrollment.status)
    }
}