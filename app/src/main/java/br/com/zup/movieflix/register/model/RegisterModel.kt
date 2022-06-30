package br.com.zup.movieflix.register.model

class RegisterModel(
    var nome: String,
    var email: String,
    var senha: String,
    var confirmaSenha: String,
    var acessAuth: Boolean = false
) {
}