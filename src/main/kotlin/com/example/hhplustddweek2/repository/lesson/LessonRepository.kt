package com.example.hhplustddweek2.repository.lesson

import com.example.hhplustddweek2.domain.Lesson
import org.springframework.stereotype.Repository

@Repository
class LessonRepository(
    private val lessonJpaRepository: LessonJpaRepository
) {
    fun findAll(): List<Lesson> {
        return lessonJpaRepository.findAll().map { it.toDomain() }
    }

    fun findById(id: Long): Lesson? {
        return lessonJpaRepository.findById(id).orElse(null)?.toDomain()
    }

    fun create(lesson: Lesson): Lesson {
        return lessonJpaRepository.save(lesson.toEntity()).toDomain()
    }
}