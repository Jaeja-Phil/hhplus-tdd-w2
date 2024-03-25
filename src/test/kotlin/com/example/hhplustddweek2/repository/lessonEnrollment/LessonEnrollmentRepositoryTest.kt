package com.example.hhplustddweek2.repository.lessonEnrollment

import com.example.hhplustddweek2.entity.LessonEnrollmentEntity
import com.example.hhplustddweek2.entity.LessonEnrollmentStatusType
import com.example.hhplustddweek2.entity.LessonEntity
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*

class LessonEnrollmentRepositoryTest {
    private val lessonEnrollmentJpaRepository: LessonEnrollmentJpaRepository = mockk()
    private val lessonEnrollmentRepository: LessonEnrollmentRepository = LessonEnrollmentRepository(lessonEnrollmentJpaRepository)

    @Test
    fun `findAll - 모든 LessonEnrollment을 가져오는지`() {
        // given
        every { lessonEnrollmentJpaRepository.findAll() } returns listOf(
            LessonEnrollmentEntity(1L, LessonEntity(1L, "name1", "description1", LocalDateTime.now(), 0), 1L, LessonEnrollmentStatusType.ENROLL),
            LessonEnrollmentEntity(2L, LessonEntity(2L, "name2", "description2", LocalDateTime.now(), 0), 2L, LessonEnrollmentStatusType.ENROLL),
        )

        // when
        val lessonEnrollments = lessonEnrollmentRepository.findAll()

        // then
        assertEquals(2, lessonEnrollments.size)
        assertEquals(1L, lessonEnrollments[0].id)
        assertEquals(2L, lessonEnrollments[1].id)
    }

    @Test
    fun `findById - id로 LessonEnrollment을 가져오는지`() {
        // given
        every { lessonEnrollmentJpaRepository.findById(1L) } returns Optional.of(
            LessonEnrollmentEntity(1L, LessonEntity(1L, "name", "description", LocalDateTime.now(), 0), 1L, LessonEnrollmentStatusType.ENROLL)
        )

        // when
        val lessonEnrollment = lessonEnrollmentRepository.findById(1L)

        // then
        assertNotNull(lessonEnrollment)
        assertEquals(1L, lessonEnrollment?.id)
    }

    @Test
    fun `findById - 존재하지 않는 id로 LessonEnrollment을 가져오려고 하면 null이 반환된다`() {
        // given
        every { lessonEnrollmentJpaRepository.findById(1L) } returns Optional.empty()

        // when
        val lessonEnrollment = lessonEnrollmentRepository.findById(1L)

        // then
        assertNull(lessonEnrollment)
    }

    @Test
    fun `create - LessonEnrollment을 생성하는지`() {
        // given
        val lessonEntity = LessonEntity(1L, "name", "description", LocalDateTime.now(), 0)
        val lessonEnrollmentEntity = LessonEnrollmentEntity(1L, lessonEntity, 1L, LessonEnrollmentStatusType.ENROLL)

        every { lessonEnrollmentJpaRepository.save(any()) } returns lessonEnrollmentEntity

        // when
        val lessonEnrollment = lessonEnrollmentRepository.create(
            lessonEnrollmentEntity.toDomain()
        )

        // then
        assertEquals(1L, lessonEnrollment.id)
        assertEquals(lessonEntity.toDomain(), lessonEnrollment.lesson)
        assertEquals(1L, lessonEnrollment.userId)
        assertEquals(LessonEnrollmentStatusType.ENROLL, lessonEnrollment.status)
    }
}