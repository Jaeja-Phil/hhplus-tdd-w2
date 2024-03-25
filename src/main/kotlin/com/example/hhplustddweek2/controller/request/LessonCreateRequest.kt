package com.example.hhplustddweek2.controller.request

import java.time.LocalDateTime

data class LessonCreateRequest(
    val name: String,
    val description: String,
    val lessonDate: LocalDateTime
)

/**
 * example json:

 */