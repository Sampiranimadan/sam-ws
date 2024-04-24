package com.example.samws.security.jwt

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.stream.Collectors


@Service
class JwtUserDetailsService : UserDetailsService {

    @Autowired
    val loginService:com.example.samws.Service?=null


    override fun loadUserByUsername(username: String): UserDetails {

        print("USERNAME:: $username")

        val user = loginService!!.getLoginDetailsByUserName(username)
                ?: throw UsernameNotFoundException("User not found with username: $username")
        val roles: List<String> = user.userType.let { listOf(it.toString()) } ?: emptyList()
        val listAuthorities: List<GrantedAuthority> = mapRolesToAuthorities(roles)
        println("ROLE :::"+roles)
        return User(user.username, user.password,true,true,true,true,listAuthorities)
    }

    private fun mapRolesToAuthorities(roles: List<String>): List<GrantedAuthority> {
        return roles.stream()
                .map { role -> SimpleGrantedAuthority(role.trim()) }
                .collect(Collectors.toList())
    }
}