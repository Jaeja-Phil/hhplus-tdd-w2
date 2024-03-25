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

    fun getLessonById(id: Long, withLock: Boolean = false): Lesson {
        return when (withLock) {
            true -> lessonRepository.findLessonByIdWithLock(id) ?: throw NotFoundException("Lesson not found")
            false -> lessonRepository.findById(id) ?: throw NotFoundException("Lesson not found")
        }
    }

    fun createLesson(lessonCreateRequest: LessonCreateRequest): Lesson {
        return lessonRepository.create(
            Lesson.newOf(lessonCreateRequest.name, lessonCreateRequest.description, lessonCreateRequest.lessonDate)
        )
    }

    fun incrementEnrollCount(lesson: Lesson): Lesson {
        return lessonRepository.update(lesson.copy(enrollCount = lesson.enrollCount + 1))
    }
}