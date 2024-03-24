package com.example.hhplustddweek2.repository.lesson

import com.example.hhplustddweek2.domain.Lesson
import com.example.hhplustddweek2.entity.LessonEntity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.time.LocalDateTime
import java.util.*

class LessonRepositoryTest {

    @Mock
    private lateinit var lessonJpaRepository: LessonJpaRepository

    @InjectMocks
    private lateinit var lessonRepository: LessonRepository

    private fun createLessonEntities(desiredCount: Int): List<LessonEntity> {
        return (1..desiredCount).map {
            LessonEntity(id = it.toLong(), name = "Lesson $it", description = "Description $it", lessonDate = LocalDateTime.now(), enrollCount = 0)
        }
    }

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `findAll - should return all lessons`() {
        // given
        val lessonEntities = createLessonEntities(2)

        // when
        `when`(lessonJpaRepository.findAll()).thenReturn(lessonEntities)
        val lessons = lessonRepository.findAll()

        // then
        assertEquals(lessonEntities.size, lessons.size)
        assertEquals(lessonEntities[0].id, lessons[0].id)
        assertEquals(lessonEntities[1].id, lessons[1].id)
    }

    @Test
    fun `findById - should return lesson by id`() {
        // given
        val lessonEntity = createLessonEntities(1).first()

        // when
        `when`(lessonJpaRepository.findById(1L)).thenReturn(Optional.of(lessonEntity))
        val lesson = lessonRepository.findById(1L)

        // then
        assertNotNull(lesson)
        assertEquals(lessonEntity.id, lesson?.id)
    }

    @Test
    fun `findById - should return null when lesson not found`() {
        // when
        `when`(lessonJpaRepository.findById(1L)).thenReturn(Optional.empty())
        val lesson = lessonRepository.findById(1L)

        // then
        assertNull(lesson)
    }

    @Test
    fun `create - should create lesson`() {
        // given
        val lesson = Lesson(id = null, // null id means new lesson
            name = "Lesson 1", description = "Description 1", lessonDate = LocalDateTime.now(), enrollCount = 0)

        // when
        `when`(lessonJpaRepository.save(lesson.toEntity()))
            .thenReturn(
                LessonEntity(id = 1L, name = lesson.name, description = lesson.description, lessonDate = lesson.lessonDate, enrollCount = lesson.enrollCount)
            )
        val createdLesson = lessonRepository.create(lesson)

        // then
        assertNotNull(createdLesson)
        assertEquals(1L, createdLesson.id)
        assertEquals(lesson.name, createdLesson.name)
        assertEquals(lesson.description, createdLesson.description)
        assertEquals(lesson.lessonDate, createdLesson.lessonDate)
        assertEquals(lesson.enrollCount, createdLesson.enrollCount)
    }
}