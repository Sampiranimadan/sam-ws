package com.example.samws.security.jwt.model

import com.example.samws.login.loginDetails
import lombok.Data
import java.io.Serializable

@Data
class JwtResponse() : Serializable {

    var jwtToken: String = ""
    var loginDetails:loginDetails?=null
}