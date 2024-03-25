package com.example.hhplustddweek2.controller

import com.example.hhplustddweek2.controller.request.LessonCreateRequest
import com.example.hhplustddweek2.service.LessonService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/lessons")
class LessonController(
    private val lessonService: LessonService
) {
    @GetMapping
    fun getAllLessons() = lessonService.getAllLessons()

    @GetMapping("/{id}")
    fun getLessonById(@PathVariable id: Long) = lessonService.getLessonById(id)

    @PostMapping
    fun createLesson(@RequestBody lessonCreateRequest: LessonCreateRequest) = lessonService.createLesson(lessonCreateRequest)
}