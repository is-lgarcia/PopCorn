package com.luisg.popcorn.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.luisg.popcorn.model.MovieDbRepository
import com.luisg.popcorn.model.retrofit.response.Movie

class MovieViewModel: ViewModel() {
    private var movieDbRepository: MovieDbRepository
    private var popularMovie: LiveData<List<Movie>>

    init {
        movieDbRepository = MovieDbRepository()
        popularMovie = movieDbRepository.popularMovies()!!
    }

    fun getPopularMovies(): LiveData<List<Movie>>{
        return popularMovie
    }
}