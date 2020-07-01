package com.luisg.popcorn.model.retrofit

import com.luisg.popcorn.model.retrofit.response.PopularMoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieDbServices {

    @GET("movie/popular")
    fun getPopularMovie(): Call<PopularMoviesResponse>
}