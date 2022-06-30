package br.com.zup.movieflix.moviedetail.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.movieflix.PREFERENCE_KEY
import br.com.zup.movieflix.SAVE_USER_PASS_FLAG_KEY
import br.com.zup.movieflix.USER_NAME_LOGIN_KEY
import br.com.zup.movieflix.USER_PASSWORD_LOGIN_KEY
import br.com.zup.movieflix.home.model.Movie
import br.com.zup.movieflix.moviedetail.datasource.DirectorLocalDataSource
import br.com.zup.movieflix.moviedetail.model.DirectorModel
import br.com.zup.movieflix.moviedetail.model.MovieWithDirectorModel
import br.com.zup.movieflix.moviedetail.repository.MovieDetailRepository

class DetailMoveidViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MovieDetailRepository(DirectorLocalDataSource())
    private var _response: MutableLiveData<MovieWithDirectorModel> = MutableLiveData()
    val response: LiveData<MovieWithDirectorModel> = _response

    private val _savedData : MutableLiveData<DirectorModel> = MutableLiveData()
    val savedData : LiveData<DirectorModel> = _savedData

    private  val _saveDataFlag : MutableLiveData<Boolean> = MutableLiveData()
    val saveDataFlag : LiveData<Boolean> = _saveDataFlag

    private val pref = application.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE)
    private val prefEditor = pref.edit()

    fun getSavedData(){
        try {
            val user = pref.getString(USER_NAME_LOGIN_KEY, "").toString()
            val password = pref.getString(USER_PASSWORD_LOGIN_KEY, "").toString()
            val savedUser = DirectorModel(user, password)
            _savedData.value = savedUser
            _saveDataFlag.value = pref.getBoolean(SAVE_USER_PASS_FLAG_KEY, false)
        }catch (e:Exception){
            Log.i("Error", "------> ${e.message}")
        }
    }

    fun authentication (movie : Movie, flagSaveData:Boolean){
        try {
            prefEditor.putBoolean(SAVE_USER_PASS_FLAG_KEY, flagSaveData)
            if(flagSaveData){
                prefEditor.putString(USER_NAME_LOGIN_KEY, movie.title)
                prefEditor.putString(USER_PASSWORD_LOGIN_KEY, movie.director)
                prefEditor.apply()
            }else{
                prefEditor.remove(USER_NAME_LOGIN_KEY)
                prefEditor.remove(USER_PASSWORD_LOGIN_KEY)
                prefEditor.apply()
            }
            _response.value = repository.getMovieWithDirector(movie)
        }catch (ex: Exception){
            Log.i("Error", "------> ${ex.message}")
        }

    }

    fun getMovieWithDirector(movie: Movie) {
        try {
            _response.value = repository.getMovieWithDirector(movie)
        } catch (ex: Exception) {
            Log.e("Error", ex.message.toString())
        }
    }
}