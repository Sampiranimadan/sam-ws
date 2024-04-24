package com.example.samws.login

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
interface loginRepo :JpaRepository <loginDetails , Long> {

    @Transactional
    fun findByusername(username: String): Optional<loginDetails>
}