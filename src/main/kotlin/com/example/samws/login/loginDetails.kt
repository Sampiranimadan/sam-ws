package com.example.samws.login

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class loginDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "login_seq")
    val loginSeq: Long = 0


    @Column(name = "user_name", nullable = false, unique = true)
    var username: String = ""

    @Column(name = "password", nullable = false)
    var password: String = ""


    @Column(name = "user_type")
    val userType: RMSUserType = RMSUserType.USER

}

enum class RMSUserType() {
    USER,
    ADMIN
}