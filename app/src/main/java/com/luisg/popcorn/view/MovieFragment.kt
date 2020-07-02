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
import com.luisg.popcorn.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment: Fragment(){

    private val model: MovieViewModel by activityViewModels()
    private lateinit var adapter: MovieAdapter
    private var popularMovies: List<Movie> = ArrayList()
    private lateinit var recyclerPopularMovie: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_movie, container, false)
        adapter = MovieAdapter(popularMovies)

        recyclerPopularMovie = root.findViewById(R.id.recyclerPopularMovies)

        recyclerPopularMovie.setHasFixedSize(true)
        recyclerPopularMovie.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerPopularMovie.adapter = adapter

        //Observe de las peliculas
        model.getPopularMovies().observe(viewLifecycleOwner, Observer {
            popularMovies = it
            adapter.setData(popularMovies)
        })

        return root
    }


}