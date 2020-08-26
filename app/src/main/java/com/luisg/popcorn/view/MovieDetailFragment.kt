package com.luisg.popcorn.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import coil.api.load
import coil.size.Scale
import com.luisg.popcorn.R
import com.luisg.popcorn.common.Constants
import com.luisg.popcorn.model.retrofit.response.MovieDetail
import com.luisg.popcorn.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie_detail.*


class MovieDetailFragment() : Fragment() {

    private val model: MovieViewModel by activityViewModels()
    var itemMovieDetail: MovieDetail = MovieDetail()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        loadMovieDetail()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }

    private fun loadMovieDetail(){
        val movieId = arguments?.get("movie_id") as Int
        model.getMovieDetail(movieId).observe(viewLifecycleOwner, Observer { movie ->
            itemMovieDetail = movie
            imageCover.load(Constants.IMAGE_BASE_URL+itemMovieDetail.backdrop_path){
                scale(Scale.FIT)
                crossfade(true)
            }
            txtTitle.text = itemMovieDetail.title
        })
    }
}