package com.luisg.popcorn.model.retrofit

import com.luisg.popcorn.common.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieDbClient {

    private val movieDbServices: MovieDbServices
    private val retrofit: Retrofit

    companion object{
        var instance: MovieDbClient? = null
            get() {
                if (field == null) {
                    instance = MovieDbClient()
                }
                return instance
            }
    }

    init {
        //incluir el Interceptor para las peticiones
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(MovieDBInterceptor())
        val client = okHttpClientBuilder.build()

        //Construir el cliente Retrofit
        retrofit = Retrofit.Builder()
            .baseUrl(Constants.URL_API_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        //Instanciamos el servivio Retrofit
        movieDbServices = retrofit.create(MovieDbServices::class.java)
    }

    fun getTheMovieDbServices () = movieDbServices
}