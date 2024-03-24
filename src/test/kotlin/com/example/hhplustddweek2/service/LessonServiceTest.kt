package com.example.hhplustddweek2.service

import com.example.hhplustddweek2.controller.request.LessonCreateRequest
import com.example.hhplustddweek2.domain.Lesson
import com.example.hhplustddweek2.repository.lesson.LessonRepository
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class LessonServiceTest {
    private val lessonRepository: LessonRepository = mockk()
    private val lessonService = LessonService(lessonRepository)

    @Test
    fun `getAllLessons - 모든 Lesson을 가져오는지`() {
        // given
        every { lessonRepository.findAll() } returns listOf(
            Lesson(1L, "name1", "description1", LocalDateTime.now(), 0),
            Lesson(2L, "name2", "description2", LocalDateTime.now(), 0),
        )

        // when
        val lessons = lessonService.getAllLessons()

        // then
        assertEquals(2, lessons.size)
        assertEquals(1L, lessons[0].id)
        assertEquals(2L, lessons[1].id)
    }

    @Test
    fun `getLessonById - id로 Lesson을 가져오는지`() {
        // given
        every { lessonRepository.findById(1L) } returns
                Lesson(id = 1L, name = "name", description = "description", lessonDate = LocalDateTime.now(), enrollCount =  0)

        // when
        val lesson = lessonService.getLessonById(1L)

        // then
        assertEquals(1L, lesson.id)
        assertEquals("name", lesson.name)
        assertEquals("description", lesson.description)
        assertEquals(0, lesson.enrollCount)
    }

    @Test
    fun `create - Lesson을 생성하는지`() {
        // given
        val lessonCreateRequest = LessonCreateRequest("name", "description", LocalDateTime.now())
        every { lessonRepository.create(any()) } returns Lesson(1L, "name", "description", LocalDateTime.now(), 0)

        // when
        val lesson = lessonService.createLesson(lessonCreateRequest)

        // then
        assertEquals(1L, lesson.id)
        assertEquals("name", lesson.name)
        assertEquals("description", lesson.description)
        assertEquals(0, lesson.enrollCount)
    }
}