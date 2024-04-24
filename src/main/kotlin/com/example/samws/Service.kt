package com.example.samws

import com.example.amdds_ws.exception.UnAuthorizedException
import com.example.samws.login.loginDetails
import com.example.samws.login.loginRepo
import com.example.samws.security.jwt.JwtAuthuncationController
import com.example.samws.security.jwt.JwtTokenUtil
import com.example.samws.security.jwt.JwtUserDetailsService
import com.example.samws.security.jwt.model.JwtRequest
import com.example.samws.security.jwt.model.JwtResponse
import io.jsonwebtoken.JwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class Service(
        val loginDetailsRepo: loginRepo
) {
//
//    @Autowired
//    val jwtAuthenticationController: JwtAuthuncationController? = null


    @Autowired
    val jwtUserDetailsService:JwtUserDetailsService?=null

    @Autowired
    val jwtTokenUtil:JwtTokenUtil?=null

    fun getLoginDetailsByUserName(username: String): loginDetails {
        return loginDetailsRepo.findByusername(username)
                .orElseThrow { UnAuthorizedException("username or password invalid") }

    }

    val bcrypt = BCryptPasswordEncoder()


    fun login(jwtRequest: JwtRequest): JwtResponse {
        val data = getLoginDetailsByUserName(jwtRequest.username)
        isPasswordValid(jwtRequest.password, data.password)
        val jwtResponse = JwtResponse()

        println("USRENAME ::SDJITN")

        val token: String
        try {
            val user = jwtUserDetailsService!!.loadUserByUsername(data.username)
            token= jwtTokenUtil!!.generateToken(user)!!
        } catch (e: JwtException) {
            throw JwtException("Error Creating Token..Please Try again later")
        }
        println("system1::$jwtRequest")
        jwtResponse.jwtToken = token
        jwtResponse.loginDetails = data
        return jwtResponse
    }

    fun isPasswordValid(raw: String, encode: String) {

        println("$raw ::: $encode")
        if (!bcrypt.matches(raw, encode)) {
            throw UnAuthorizedException("username or password invalid")
        }
    }

    fun internalLoginRegister(loginDetails: loginDetails): loginDetails {
        loginDetails.password = bcrypt.encode(loginDetails.password)
        return loginDetailsRepo.save(loginDetails)
    }
}