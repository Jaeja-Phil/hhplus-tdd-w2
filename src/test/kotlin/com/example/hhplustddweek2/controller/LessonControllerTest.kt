package com.example.hhplustddweek2.controller

import com.example.hhplustddweek2.domain.Lesson
import com.example.hhplustddweek2.repository.lesson.LessonRepository
import com.example.hhplustddweek2.service.LessonService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.transaction.annotation.Transactional


@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class LessonControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var lessonController: LessonController

    @Autowired
    private lateinit var lessonService: LessonService

    @Autowired
    private lateinit var lessonRepository: LessonRepository

    private fun createLessons(desiredCount: Int): List<Lesson> {
        return (1..desiredCount).map {
            Lesson(
                id = it.toLong(),
                name = "name$it",
                description = "description$it",
                lessonDate = LocalDateTime.now(),
                enrollCount = 0
            )
        }
    }

    @AfterEach
    fun tearDown() {
        // TODO: clear repository
    }

    @Test
    fun `getAllLessons - 특강이 없다면 빈 배열을 반환한다`() {
        //given

        // when & then
        mockMvc.perform(get("/lessons"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.size()").value(0))
            .andDo(print())
    }

    @Test
    fun `getAllLessons - 모든 Lesson을 가져오는지`() {
        // given
        val lessons = createLessons(2)
        lessons.forEach { lessonRepository.create(it) }

        // when & then
        mockMvc.perform(get("/lessons"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.size()").value(2))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[1].id").value(2))
            .andDo(print())
    }

    @Test
    fun `getLessonById - id로 Lesson을 가져오는지`() {
        // given
        val lesson = lessonRepository.create(Lesson.newOf(
            name = "name",
            description = "description",
            lessonDate = LocalDateTime.now(),
        ))

        // when & then
        mockMvc.perform(get("/lessons/${lesson.id}"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(lesson.id))
            .andExpect(jsonPath("$.name").value("name"))
            .andExpect(jsonPath("$.description").value("description"))
            .andExpect(jsonPath("$.enrollCount").value(0))
            .andDo(print())
    }

    @Test
    fun `getLessonById - 존재하지 않는 id로 조회하면 NotFoundException 에러가 발생한다`() {
        // given

        // when & then
        mockMvc.perform(get("/lessons/1"))
            .andExpect(status().isNotFound)
            .andDo(print())
    }
}