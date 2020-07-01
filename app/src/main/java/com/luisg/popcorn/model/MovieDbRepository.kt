package com.luisg.popcorn.model


import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.luisg.popcorn.common.MyApp
import com.luisg.popcorn.model.retrofit.MovieDbClient
import com.luisg.popcorn.model.retrofit.MovieDbServices
import com.luisg.popcorn.model.retrofit.response.Movie
import com.luisg.popcorn.model.retrofit.response.PopularMoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDbRepository {
    var movieDbServices: MovieDbServices? = null
    var movieDbClient: MovieDbClient? = null
    var popularMovies: MutableLiveData<List<Movie>>? = null

    init {
        movieDbClient = MovieDbClient.instance
        movieDbServices = movieDbClient?.getTheMovieDbServices()
        popularMovies

    }

    fun popularMovies(): MutableLiveData<List<Movie>>? {
        if (popularMovies == null) {
            popularMovies = MutableLiveData<List<Movie>>()
        }

        val call: Call<PopularMoviesResponse>? = movieDbServices?.getPopularMovie()
        call?.enqueue(object : Callback<PopularMoviesResponse> {
            override fun onFailure(call: Call<PopularMoviesResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error en la conexión", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<PopularMoviesResponse>,
                response: Response<PopularMoviesResponse>
            ) {
                if (response.isSuccessful) {
                    popularMovies?.value = response.body()?.results
                }
            }

        })

        return popularMovies
    }
}