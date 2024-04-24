package com.example.amdds_ws.exception

import com.example.samws.dto.CustomerResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice
class CustomExceptionHandling {


    @ExceptionHandler(CustomException::class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    fun internalerror(customexception: CustomException): CustomerResponse //Custom response class name
    {
        return CustomerResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), customexception.msg)
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    fun notfound(notFoundException: NotFoundException): CustomerResponse {
        return CustomerResponse(HttpStatus.NOT_FOUND.value(), notFoundException.msg)
    }

    @ExceptionHandler(UnAuthorizedException::class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    fun unauthorized(unAuthorizedException: UnAuthorizedException): CustomerResponse {
        return CustomerResponse(HttpStatus.NOT_FOUND.value(), unAuthorizedException.msg)

    }


    @ExceptionHandler(DataConflictException::class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    fun conflict(conflictException: DataConflictException): CustomerResponse {
        return CustomerResponse(HttpStatus.CONFLICT.value(), conflictException.msg)
    }

}