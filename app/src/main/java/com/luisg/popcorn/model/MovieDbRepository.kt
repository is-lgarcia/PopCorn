package com.luisg.popcorn.model


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.luisg.popcorn.common.MyApp
import com.luisg.popcorn.model.retrofit.MovieDbClient
import com.luisg.popcorn.model.retrofit.MovieDbServices
import com.luisg.popcorn.model.retrofit.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDbRepository {
    var movieDbServices: MovieDbServices? = null
    var movieDbClient: MovieDbClient? = null
    var popularMovies: MutableLiveData<List<Movie>>? = null
    var topRatedMovies: MutableLiveData<List<TopRatedMovie>>? = null
    var movieDetail: MutableLiveData<MovieDetail>? = null

    //var listGenres: MutableLiveData<List<GenreItemResult>>? = null

    init {
        movieDbClient = MovieDbClient.instance
        movieDbServices = movieDbClient?.getTheMovieDbServices()
        popularMovies()
        topRatedMovies()
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

    fun topRatedMovies(): MutableLiveData<List<TopRatedMovie>>? {
        if (topRatedMovies == null) {
            topRatedMovies = MutableLiveData<List<TopRatedMovie>>()
        }

        val call: Call<TopRatedMoviesResponse>? = movieDbServices?.getTopRated()
        call?.enqueue(object : Callback<TopRatedMoviesResponse>{
            override fun onFailure(call: Call<TopRatedMoviesResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error en la conexión", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<TopRatedMoviesResponse>,
                response: Response<TopRatedMoviesResponse>
            ) {
                if (response.isSuccessful){
                    topRatedMovies?.value = response.body()?.results
                }
            }

        })

        return topRatedMovies
    }

    fun getDatailMovie(movie_id: Int): MutableLiveData<MovieDetail>? {
        if (movieDetail == null)
            movieDetail = MutableLiveData()

        val call = movieDbServices?.getMovieDetail(movie_id)

        call?.enqueue(object : Callback<MovieDetail>{
            override fun onFailure(call: Call<MovieDetail>, t: Throwable) {
                Toast.makeText(MyApp.instance,"Error de conexión.",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MovieDetail>, response: Response<MovieDetail>) {

                if (response.isSuccessful){
                    movieDetail?.value = response.body()
                } else {
                    Toast.makeText(MyApp.instance,"Algo a ido mal.",Toast.LENGTH_SHORT).show()
                }
            }
        })

        return movieDetail
    }


}