package com.example.hhplustddweek2.entity

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class LessonEnrollmentEntityTest {
    @Test
    fun `toDomain - domain으로 정상적으로 변환하는지`() {
        // given
        val lessonEntity = LessonEntity(
            id = 1L,
            name = "name",
            description = "description",
            lessonDate = LocalDateTime.now(),
            enrollCount = 0
        )
        val lessonEnrollmentEntity = LessonEnrollmentEntity(
            id = 1L,
            lesson = lessonEntity,
            userId = 1L,
            status = LessonEnrollmentStatusType.ENROLL
        )

        // when
        val lessonEnrollment = lessonEnrollmentEntity.toDomain()

        // then
        assertEquals(lessonEnrollmentEntity.id, lessonEnrollment.id)
        assertEquals(lessonEnrollmentEntity.lesson.toDomain(), lessonEnrollment.lesson)
        assertEquals(lessonEnrollmentEntity.userId, lessonEnrollment.userId)
        assertEquals(lessonEnrollmentEntity.status, lessonEnrollment.status)
    }
}