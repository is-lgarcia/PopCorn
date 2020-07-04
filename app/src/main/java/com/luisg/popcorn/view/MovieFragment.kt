package com.luisg.popcorn.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luisg.popcorn.R
import com.luisg.popcorn.model.retrofit.response.Movie
import com.luisg.popcorn.model.retrofit.response.TopRatedMovie
import com.luisg.popcorn.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment: Fragment(){

    private val model: MovieViewModel by activityViewModels()
    private lateinit var adapter: MovieAdapter
    private lateinit var adapterRated: TopRatedAdapter
    private var popularMovies: List<Movie> = ArrayList()
    private var topRatedMovie: List<TopRatedMovie> = ArrayList()
    private lateinit var recyclerPopularMovie: RecyclerView
    private lateinit var recyclerTopRatedMovie: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_movie, container, false)

        loadPopularMovies(root)
        loadTopRatedMovies(root)

        return root
    }

    private fun loadTopRatedMovies(root: View) {
        adapterRated = TopRatedAdapter(topRatedMovie)
        recyclerTopRatedMovie = root.findViewById(R.id.recyclerTopRatedMovies)
        recyclerTopRatedMovie.setHasFixedSize(true)
        recyclerTopRatedMovie.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL, false
        )
        recyclerTopRatedMovie.adapter = adapterRated

        model.getTopRatedMovies().observe(viewLifecycleOwner, Observer {
            topRatedMovie = it
            adapterRated.setData(topRatedMovie)
        })
    }

    private fun loadPopularMovies(root: View) {
        adapter = MovieAdapter(popularMovies)
        recyclerPopularMovie = root.findViewById(R.id.recyclerPopularMovies)
        recyclerPopularMovie.setHasFixedSize(true)
        recyclerPopularMovie.layoutManager = LinearLayoutManager(
            activity,
            LinearLayoutManager.HORIZONTAL, false
        )
        recyclerPopularMovie.adapter = adapter

        model.getPopularMovies().observe(viewLifecycleOwner, Observer {
            popularMovies = it
            adapter.setData(popularMovies)
        })
    }


}