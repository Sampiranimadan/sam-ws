package com.example.samws.security.jwt.cofi

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.io.Serializable
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthuncationEntryPoint : AuthenticationEntryPoint,Serializable {

    override fun commence(
            request: HttpServletRequest?,
            response: HttpServletResponse?,
            authException: AuthenticationException?
    ) {
        println("ERROR $request")
        response!!.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}