package com.luisg.popcorn.model.retrofit

import com.luisg.popcorn.common.Constants
import okhttp3.Interceptor
import okhttp3.Response

class MovieDBInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //Añadiendo parametros en la URL de la cadena que recibimos (CHAIN)
        val urlWhithParams = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(Constants.URL_PARAM_API_KEY, Constants.API_KEY_MOVIE_DB)
            .addQueryParameter(Constants.URL_PARAM_API_KEY, Constants.SPANISH_LANGUAGE)
            .build()

        var request = chain.request()

        request = request.newBuilder()
            .url(urlWhithParams)
            .addHeader("Content-Type", "aplication/json")
            .addHeader("Accept", "aplication/json")
            .build()

        return chain.proceed(request)
    }
}