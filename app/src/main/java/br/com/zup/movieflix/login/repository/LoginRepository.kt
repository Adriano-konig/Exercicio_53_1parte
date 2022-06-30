package br.com.zup.movieflix.login.repository

import br.com.zup.movieflix.login.model.LoginModel

class LoginRepository {
    fun authenticate (login : LoginModel) :LoginModel{

        if(login.user == "usuario" && login.password == "1234"){
            login.accessAuth = true
        }else{
            login.accessAuth = false
        }

        return login
    }
}