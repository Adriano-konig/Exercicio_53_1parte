package br.com.zup.movieflix

import br.com.zup.movieflix.register.model.RegisterModel
import br.com.zup.movieflix.register.repository.RegisterRepository
import org.junit.Test
import org.junit.Assert.*


class TesteRegisterRepository {

    @Test
    fun registerUser(){
        val registerRepository = RegisterRepository()
        val user = RegisterModel(
            "Felipe",
            "felipe.dearaujo@zup.com.br",
        "melhorProfessorDoMundo",
            ""
        )

        val resultado = registerRepository.authenticate(user)

            assertEquals(resultado.nome, "Felipe")
            assertEquals(resultado.email, "felipe.dearaujo@zup.com.br")
            assertEquals(resultado.senha, "melhorProfessorDoMundo")
    }
}