package com.luisg.popcorn.model.retrofit

import com.luisg.popcorn.model.retrofit.response.Movie
import com.luisg.popcorn.model.retrofit.response.MovieDetail
import com.luisg.popcorn.model.retrofit.response.PopularMoviesResponse
import com.luisg.popcorn.model.retrofit.response.TopRatedMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbServices {

    @GET("movie/popular")
    fun getPopularMovie(): Call<PopularMoviesResponse>

    @GET("movie/top_rated")
    fun getTopRated(): Call<TopRatedMoviesResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movie_id: Int): Call<MovieDetail>

    /*@GET("genre/movie/list")
    fun loadGenre(): Call<GenreItemResult>*/


}