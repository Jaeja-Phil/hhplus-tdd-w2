package com.example.hhplustddweek2.repository.lesson

import com.example.hhplustddweek2.domain.Lesson
import com.example.hhplustddweek2.entity.LessonEntity
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

    fun findLessonByIdWithLock(id: Long): Lesson? {
        return lessonJpaRepository.findLessonByIdWithLock(id).orElse(null)?.toDomain()
    }

    fun create(lesson: Lesson): Lesson {
        return lessonJpaRepository.save(
            LessonEntity(
                id = null,
                name = lesson.name,
                description = lesson.description,
                lessonDate = lesson.lessonDate,
                enrollCount = 0
            )
        ).toDomain()
    }

    fun update(lesson: Lesson): Lesson {
        return lessonJpaRepository.save(
            LessonEntity(
                id = lesson.id ?: throw IllegalArgumentException("id should not be null"),
                name = lesson.name,
                description = lesson.description,
                lessonDate = lesson.lessonDate,
                enrollCount = lesson.enrollCount
            )
        ).toDomain()
    }
}