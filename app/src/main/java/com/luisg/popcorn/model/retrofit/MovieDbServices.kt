package com.luisg.popcorn.model.retrofit

import com.luisg.popcorn.model.retrofit.response.MovieDetail
import com.luisg.popcorn.model.retrofit.response.MoviesResponse
import com.luisg.popcorn.model.retrofit.response.TopRatedMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbServices {

    @GET("movie/popular")
    fun getPopularMovie(): Call<MoviesResponse>

    @GET("movie/top_rated")
    fun getTopRated(): Call<TopRatedMoviesResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movie_id: Int): Call<MovieDetail>

    @GET("search/movie")
    fun getSearchMovie(@Query("query") query: String): Call<MoviesResponse>

    /*@GET("genre/movie/list")
    fun loadGenre(): Call<GenreItemResult>*/


}