package com.example.hhplustddweek2.domain

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.LocalDateTime

class LessonTest {

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

        // when
        val lessonEntity = lesson.toEntity()

        // then
        assertEquals(lesson.id, lessonEntity.id)
        assertEquals(lesson.name, lessonEntity.name)
        assertEquals(lesson.description, lessonEntity.description)
        assertEquals(lesson.lessonDate, lessonEntity.lessonDate)
        assertEquals(lesson.enrollCount, lessonEntity.enrollCount)
    }
}