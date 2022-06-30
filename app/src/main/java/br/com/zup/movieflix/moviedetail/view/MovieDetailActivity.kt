package br.com.zup.movieflix.moviedetail.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.zup.movieflix.CHAVE_MOVIE
import br.com.zup.movieflix.R
import br.com.zup.movieflix.databinding.ActivityMovieDetailBinding
import br.com.zup.movieflix.home.model.Movie
import br.com.zup.movieflix.home.view.HomeActivity
import br.com.zup.movieflix.moviedetail.model.DirectorModel
import br.com.zup.movieflix.moviedetail.viewmodel.DetailMoveidViewModel

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    private val viewModel: DetailMoveidViewModel by lazy {
        ViewModelProvider(this)[DetailMoveidViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observable()
        getPassedData()


    }

//    private fun authenticate(){
//        val title = binding.tvMovieTitle.text.toString()
//        val sinopse = binding.tvMovieSinopse.text.toString()
//        val image = binding.imagemView.setImageResource()
//        val name = binding.tvDirectorName.text.toString()
//        val info = binding.tvDirectorInfo.text.toString()
//        val detalhes = Movie(title,sinopse,image,name)
//        viewModel.authentication(detalhes, binding.switch2.isChecked)
//    }

    fun getPassedData(){
        val movie = intent.getParcelableExtra<Movie>(CHAVE_MOVIE)
        movie?.let { viewModel.getMovieWithDirector(it)}
        }
    fun observable(){
        viewModel.response.observe(this){
            binding.imagemView.setImageResource(it.movie.image)
            binding.tvMovieTitle.text = it.movie.title
            binding.tvMovieSinopse.text = it.movie.description
            binding.tvDirectorName.text = it.director.name
            binding.tvDirectorInfo.text = it.director.info
        }
        viewModel.savedData.observe(this){
        }
        viewModel.saveDataFlag.observe(this){
            binding.switch2.isChecked = it
        }
    }
}