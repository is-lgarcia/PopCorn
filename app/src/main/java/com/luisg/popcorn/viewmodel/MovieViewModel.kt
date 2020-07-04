package com.luisg.popcorn.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.luisg.popcorn.model.MovieDbRepository
import com.luisg.popcorn.model.retrofit.response.Movie
import com.luisg.popcorn.model.retrofit.response.TopRatedMovie

class MovieViewModel: ViewModel() {
    private var movieDbRepository: MovieDbRepository
    private var popularMovie: LiveData<List<Movie>>
    private var topRatedMovie: LiveData<List<TopRatedMovie>>

    init {
        movieDbRepository = MovieDbRepository()
        popularMovie = movieDbRepository.popularMovies()!!
        topRatedMovie = movieDbRepository.topRatedMovies()!!
    }

    fun getPopularMovies(): LiveData<List<Movie>>{
        return popularMovie
    }

    fun getTopRatedMovies(): LiveData<List<TopRatedMovie>>{
        return topRatedMovie
    }
}