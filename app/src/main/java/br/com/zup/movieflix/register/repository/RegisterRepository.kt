package br.com.zup.movieflix.register.repository


import br.com.zup.movieflix.register.model.RegisterModel

class RegisterRepository {
    fun authenticate (register: RegisterModel) : RegisterModel {

        if(register.senha == register.confirmaSenha){
            register.acessAuth = true
        }else{
            register.acessAuth = false
        }
        return register
    }
}