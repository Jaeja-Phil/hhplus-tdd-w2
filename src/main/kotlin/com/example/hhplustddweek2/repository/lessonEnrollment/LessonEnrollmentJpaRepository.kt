package com.example.hhplustddweek2.repository.lessonEnrollment

import com.example.hhplustddweek2.entity.LessonEnrollmentEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LessonEnrollmentJpaRepository : JpaRepository<LessonEnrollmentEntity, Long>