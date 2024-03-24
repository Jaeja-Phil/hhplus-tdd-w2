package com.example.hhplustddweek2.repository.lesson

import com.example.hhplustddweek2.entity.LessonEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LessonJpaRepository : JpaRepository<LessonEntity, Long>