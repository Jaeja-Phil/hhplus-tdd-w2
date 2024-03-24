package com.example.hhplustddweek2.service

import com.example.hhplustddweek2.controller.request.LessonCreateRequest
import com.example.hhplustddweek2.domain.Lesson
import com.example.hhplustddweek2.error.NotFoundException
import com.example.hhplustddweek2.repository.lesson.LessonRepository
import org.springframework.stereotype.Service

@Service
class LessonService(
    private val lessonRepository: LessonRepository
) {
    fun getAllLessons() = lessonRepository.findAll()

    fun getLessonById(id: Long): Lesson {
        return lessonRepository.findById(id) ?: throw NotFoundException("Lesson not found")
    }

    fun createLesson(lessonCreateRequest: LessonCreateRequest): Lesson {
        return lessonRepository.create(
            Lesson.newOf(lessonCreateRequest.name, lessonCreateRequest.description, lessonCreateRequest.lessonDate)
        )
    }
}