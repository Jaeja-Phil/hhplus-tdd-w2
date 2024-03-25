package com.example.hhplustddweek2.controller

import com.example.hhplustddweek2.application.lesson.EnrollLessonApplication
import com.example.hhplustddweek2.controller.request.LessonEnrollmentRequest
import com.example.hhplustddweek2.domain.LessonEnrollment
import com.example.hhplustddweek2.service.LessonEnrollmentService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/lesson-enrollments")
class LessonEnrollmentController(
    private val lessonEnrollmentService: LessonEnrollmentService,
    private val enrollLessonApplication: EnrollLessonApplication
) {
    @GetMapping("/check")
    fun checkEnrollment(
        @RequestParam lessonId: Long,
        @RequestParam userId: Long
    ): LessonEnrollment {
        return lessonEnrollmentService.checkEnrollment(lessonId, userId)
    }

    @PostMapping("/enroll")
    fun enroll(
        @RequestBody lessonEnrollmentRequest: LessonEnrollmentRequest
    ): LessonEnrollment {
        return enrollLessonApplication.run(lessonEnrollmentRequest.lessonId, lessonEnrollmentRequest.userId)
    }
}
