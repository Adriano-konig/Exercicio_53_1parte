package br.com.zup.movieflix.register.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.zup.movieflix.databinding.ActivityRegisterBinding
import br.com.zup.movieflix.home.view.HomeActivity
import br.com.zup.movieflix.register.model.RegisterModel
import br.com.zup.movieflix.register.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observable()

    binding.bvLogin.setOnClickListener {
        val nome = binding.etUserNameRegister.text.toString()
        val email = binding.etEmailRegister.text.toString()
        val senha = binding.etPasswordRegister.text.toString()
        val confirmaSenha = binding.etConfirmPasswordRegister.text.toString()

        var register = RegisterModel(nome, email, senha, confirmaSenha)
        viewModel.authentication(register)
    }
    }
    fun observable(){
        viewModel.response.observe(this) {
            if (it.acessAuth) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                Toast.makeText(this, "Senha precisa ser iguais", Toast.LENGTH_LONG).show()
            }
        }
    }
}
