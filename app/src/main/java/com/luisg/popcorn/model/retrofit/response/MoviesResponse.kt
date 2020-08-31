package com.luisg.popcorn.model.retrofit.response

import com.luisg.popcorn.model.retrofit.response.data.Movie

data class MoviesResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)