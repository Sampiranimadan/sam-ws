package com.example.samws.security.jwt

import com.example.samws.Service
import com.example.samws.security.jwt.model.JwtRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors

@RestController
@CrossOrigin
class JwtAuthuncationController {
    @Autowired
    private val authenticationManager: AuthenticationManager? = null

    @Autowired
    private val jwtTokenUtil: JwtTokenUtil? = null

    @Autowired
    private val userDetailsService: JwtUserDetailsService? = null

    @Autowired
    private val loginService: Service? = null


    //    @RequestMapping(value = ["/authenticate"], method = [RequestMethod.POST])
//    @Throws(Exception::class)
//    fun createAuthenticationToken(@RequestBody authenticationRequest: JwtRequest): String {
//        //  authenticate(authenticationRequest.username, authenticationRequest.password);
//        val userDetails = userDetailsService
//                ?.loadUserByUsername(authenticationRequest.username)
//      //  loginService!!.isPasswordValid( authenticationRequest.password,userDetails!!.password)
//
//        val token = jwtTokenUtil!!.generateToken(userDetails!!);
//        return token.toString()
//    }


//    fun createTokenForJudge(email: String): JudgeDetailsDto {
//        val judgeUser = loginService!!.getRegByEmail(email)
//            ?: throw UsernameNotFoundException("User not found with username: $email")
//        val roles: List<String> = judgeUser.userType.let { listOf(it.toString()) } ?: emptyList()
//        val listAuthorities: List<GrantedAuthority> = mapRolesToAuthorities(roles)
//        println("ROLE :::" + roles)
//        val user = User(judgeUser.email, "sdg@tgwe34413aetassfewef", true, true, true, true, listAuthorities)
//        val token = jwtTokenUtil!!.generateToken(user);
//
//        val judgeDetailsDto = JudgeDetailsDto()
//        judgeDetailsDto.judgeDetails = judgeUser
//        judgeDetailsDto.token = token!!
//        return judgeDetailsDto
//    }

    private fun mapRolesToAuthorities(roles: List<String>): List<GrantedAuthority> {
        return roles.stream()
                .map { role -> SimpleGrantedAuthority(role.trim()) }
                .collect(Collectors.toList())
    }

    @Throws(Exception::class)
    private fun authenticate(username: String, password: String) {
        try {

            authenticationManager!!.authenticate(UsernamePasswordAuthenticationToken(username, password))
        } catch (e: DisabledException) {
            throw Exception("USER_DISABLED", e)
        } catch (e: BadCredentialsException) {
            throw Exception("INVALID_CREDENTIALS", e)
        }
    }

}