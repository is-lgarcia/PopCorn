package com.luisg.popcorn.view.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import coil.api.load
import coil.size.Scale
import com.luisg.popcorn.R
import com.luisg.popcorn.common.Constants
import com.luisg.popcorn.model.retrofit.response.data.MovieDetail
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
            imagePoster.load(Constants.IMAGE_BASE_URL+itemMovieDetail.poster_path){
                scale(Scale.FIT)
                crossfade(true)
            }
            txtOverview.text = itemMovieDetail.overview
            txtTagline.text = itemMovieDetail.tagline
            progressRanking.setProgressWithAnimation(
                (itemMovieDetail.vote_average * 10).toFloat(),
                1000,
                AccelerateDecelerateInterpolator(),
                500
            )
            txtRanking.text = itemMovieDetail.vote_average.toString()
        })
    }
}