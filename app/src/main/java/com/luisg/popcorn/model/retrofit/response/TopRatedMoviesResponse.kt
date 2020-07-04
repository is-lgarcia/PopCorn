package com.luisg.popcorn.model.retrofit.response

data class TopRatedMoviesResponse(
    val page: Int,
    val results: List<TopRatedMovie>,
    val total_pages: Int,
    val total_results: Int
)