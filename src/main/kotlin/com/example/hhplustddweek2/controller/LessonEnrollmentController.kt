package com.example.hhplustddweek2.controller

import com.example.hhplustddweek2.service.LessonEnrollmentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/lesson-enrollments")
class LessonEnrollmentController(
    private val lessonEnrollmentService: LessonEnrollmentService
) {
    @GetMapping("/check")
    fun checkEnrollment(
        @RequestParam lessonId: Long,
        @RequestParam userId: Long
    ): Boolean {
        return lessonEnrollmentService.checkEnrollment(lessonId, userId)
    }
}