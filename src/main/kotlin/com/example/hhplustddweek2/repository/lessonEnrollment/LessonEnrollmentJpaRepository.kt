package com.example.hhplustddweek2.repository.lessonEnrollment

import com.example.hhplustddweek2.entity.LessonEnrollmentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LessonEnrollmentJpaRepository : JpaRepository<LessonEnrollmentEntity, Long> {
    fun findAllByUserId(userId: Long): List<LessonEnrollmentEntity>
    fun findAllByLessonId(lessonId: Long): List<LessonEnrollmentEntity>
    fun findByUserIdAndLessonId(userId: Long, lessonId: Long): LessonEnrollmentEntity?
}