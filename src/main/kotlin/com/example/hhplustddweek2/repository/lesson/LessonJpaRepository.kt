package com.example.hhplustddweek2.repository.lesson

import com.example.hhplustddweek2.entity.LessonEntity
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface LessonJpaRepository : JpaRepository<LessonEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT le FROM LessonEntity le WHERE le.id = :id")
    fun findLessonByIdWithLock(id: Long): Optional<LessonEntity>
}