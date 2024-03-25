package com.example.hhplustddweek2.controller

import com.example.hhplustddweek2.domain.Lesson
import com.example.hhplustddweek2.domain.LessonEnrollment
import com.example.hhplustddweek2.repository.lesson.LessonRepository
import com.example.hhplustddweek2.repository.lessonEnrollment.LessonEnrollmentRepository
import com.example.hhplustddweek2.service.LessonEnrollmentService
import jakarta.transaction.Transactional
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

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class LessonEnrollmentControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var lessonEnrollmentController: LessonEnrollmentController

    @Autowired
    private lateinit var lessonEnrollmentService: LessonEnrollmentService

    @Autowired
    private lateinit var lessonEnrollmentRepository: LessonEnrollmentRepository

    @Autowired
    private lateinit var lessonRepository: LessonRepository

    @Test
    fun `checkEnrollment - 수강신청이 되어있으면 true를 반환한다`() {
        // given
        val userId = 1L
        val lesson = lessonRepository.create(
            Lesson.newOf("name", "description", LocalDateTime.now())
        )
        lessonEnrollmentRepository.create(
            LessonEnrollment.newOf(
                Lesson(id = lesson.id, name = "name", description = "description", lessonDate = LocalDateTime.now(), enrollCount = 0),
                userId
            )
        )

        // when & then
        mockMvc.perform(get("/lesson-enrollments/check")
            .param("lessonId", lesson.id.toString())
            .param("userId", userId.toString())
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("\$").value(true))
    }

    @Test
    fun `checkEnrollment - 수강신청이 되어있지 않으면 false를 반환한다`() {
        // given
        val userId = 1L
        val lesson = lessonRepository.create(
            Lesson.newOf("name", "description", LocalDateTime.now())
        )

        // when & then
        mockMvc.perform(get("/lesson-enrollments/check")
            .param("lessonId", lesson.id.toString())
            .param("userId", userId.toString())
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("\$").value(false))
    }
}