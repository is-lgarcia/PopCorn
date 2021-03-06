package com.luisg.popcorn.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luisg.popcorn.model.MovieDbRepository
import com.luisg.popcorn.model.retrofit.response.data.Movie
import com.luisg.popcorn.model.retrofit.response.data.MovieDetail

class MovieViewModel: ViewModel() {
    private var movieDetail: MutableLiveData<MovieDetail> = MutableLiveData()
    private var movieDbRepository: MovieDbRepository
    private var popularMovie: LiveData<List<Movie>>
    private var searchMovie: LiveData<List<Movie>> = MutableLiveData()
    private var topMovie: LiveData<List<Movie>>

    init {
        movieDbRepository = MovieDbRepository()
        popularMovie = movieDbRepository.popularMovies()!!
        topMovie = movieDbRepository.topRatedMovies()!!
    }

    fun getPopularMovies(): LiveData<List<Movie>>{
        return popularMovie
    }

    fun getTopRatedMovies(): LiveData<List<Movie>>{
        return topMovie
    }

    fun getMovieDetail(movie_id: Int): LiveData<MovieDetail> {
        movieDetail = movieDbRepository.getDatailMovie(movie_id)!!
        return movieDetail
    }

    fun getSearchMovie(query: String): LiveData<List<Movie>>{
        searchMovie = movieDbRepository.getSearchMovie(query)!!
        return searchMovie

    }
}