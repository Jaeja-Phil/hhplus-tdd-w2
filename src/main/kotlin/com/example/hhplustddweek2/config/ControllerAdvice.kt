package com.example.hhplustddweek2.config

import com.example.hhplustddweek2.error.ConflictException
import com.example.hhplustddweek2.error.MaxEnrollCountException
import com.example.hhplustddweek2.error.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception

data class ErrorResponse(val code: String, val message: String)
@ControllerAdvice
class ControllerAdvice {
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(e: NotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse("404", e.message ?: "Not found"),
            HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(ConflictException::class)
    fun handleConflictException(e: ConflictException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse("409", e.message ?: "Conflict"),
            HttpStatus.CONFLICT
        )
    }

    @ExceptionHandler(MaxEnrollCountException::class)
    fun handleMaxEnrollCountException(e: MaxEnrollCountException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse("400", e.message ?: "Max enroll count"),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse("500", "Internal server error"),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleMissingServletRequestParameterException(e: MissingServletRequestParameterException): ResponseEntity<ErrorResponse> {
        return ResponseEntity(
            ErrorResponse("400", e.message ?: "Bad request"),
            HttpStatus.BAD_REQUEST
        )
    }
}