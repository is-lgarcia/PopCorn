package com.luisg.popcorn.view.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.luisg.popcorn.R
import com.luisg.popcorn.model.retrofit.response.data.Movie
import com.luisg.popcorn.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_search_movie.*

class SearchMovieFragment: Fragment(), MovieListener {

    private val model: MovieViewModel by activityViewModels()
    private lateinit var adapter: MovieAdapter
    private var listMovies: List<Movie> = ArrayList()
    private lateinit var recyclerMovie: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search_movie, container, false)

        loadlistMovies(root)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun loadlistMovies(root: View) {
        adapter = MovieAdapter(listMovies, this)
        recyclerMovie = root.findViewById(R.id.rvSearchMovie)
        recyclerMovie.setHasFixedSize(true)
        recyclerMovie.layoutManager = GridLayoutManager(
            activity,
            3,
            GridLayoutManager.VERTICAL,
            false)
        recyclerMovie.adapter = adapter

        val query = arguments?.get("query") as String
        model.getSearchMovie(query).observe(viewLifecycleOwner, Observer {
            listMovies = it
            adapter.setData(listMovies)
        })
    }

    override fun getIdClicked(movie_id: Int) {
        val bundle = bundleOf("movie_id" to movie_id)
        findNavController().navigate(R.id.action_searchMovieFragment_to_movieDetailFragment, bundle)
    }
}