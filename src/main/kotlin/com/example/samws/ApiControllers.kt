package com.example.samws

import com.example.samws.dto.CustomerResponse
import com.example.samws.login.loginDetails
import com.example.samws.security.jwt.model.JwtRequest
import com.example.samws.security.jwt.model.JwtResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/RMS/")
class ApiControllers( private val  rmsService : Service) {



    @PostMapping("/auth/register")
    fun register(@RequestBody loginDetails: loginDetails): CustomerResponse {
        rmsService.internalLoginRegister(loginDetails)

        return CustomerResponse(200, "username ${loginDetails.username} added")
    }


    @PostMapping("/auth/login")
    fun login(@RequestBody loginDetails: JwtRequest): JwtResponse {
        return rmsService.login(loginDetails)

    }
}