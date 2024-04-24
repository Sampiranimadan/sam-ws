package com.example.amdds_ws.exception

data class CustomException (val msg:String):RuntimeException(msg)

data class NotFoundException (val msg:String):RuntimeException(msg)

data class DataConflictException (val msg:String):RuntimeException(msg)

data class UnAuthorizedException (val msg:String):RuntimeException(msg)

data class MissingValueException(val msg: String):RuntimeException(msg)



data class IllegalAccessException(val msg:String):RuntimeException(msg)