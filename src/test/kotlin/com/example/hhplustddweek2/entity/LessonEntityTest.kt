package com.example.hhplustddweek2.entity

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.LocalDateTime

class LessonEntityTest {

    @Test
    fun `toDomain - domain 으로 정상적으로 변환하는지`() {
        // given
        val lessonEntity = LessonEntity(
            id = 1L,
            name = "name",
            description = "description",
            lessonDate = LocalDateTime.now(),
            enrollCount = 0
        )

        // when
        val lesson = lessonEntity.toDomain()

        // then
        assertEquals(lessonEntity.id, lesson.id)
        assertEquals(lessonEntity.name, lesson.name)
        assertEquals(lessonEntity.description, lesson.description)
        assertEquals(lessonEntity.lessonDate, lesson.lessonDate)
        assertEquals(lessonEntity.enrollCount, lesson.enrollCount)
    }
}