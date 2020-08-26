package com.luisg.popcorn.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.luisg.popcorn.R
import com.luisg.popcorn.common.Constants
import com.luisg.popcorn.common.MyApp
import com.luisg.popcorn.model.retrofit.response.Movie

class MovieAdapter(var mValues: List<Movie>, val movieListener: MovieListener) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mValues.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]

        holder.txtTitleMovie.text = item.title
        holder.txtGenre.text = item.genre_ids.toString()
        holder.txtAverege.text = item.vote_average.toString()
        holder.imageCover.load(Constants.IMAGE_BASE_URL + item.poster_path){
            crossfade(true)
            placeholder(R.drawable.ic_cine)
        }
        holder.itemView.setOnClickListener{
            movieListener.getIdClicked(item.id)
        }
    }

    fun setData(popularMovies: List<Movie>){
        mValues = popularMovies
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageCover: ImageView = itemView.findViewById(R.id.id_image_cover)
        val txtTitleMovie: TextView = itemView.findViewById(R.id.txtTitle)
        val txtGenre: TextView = itemView.findViewById(R.id.txt_genre)
        val txtAverege: TextView = itemView.findViewById(R.id.txt_ranking)

    }


}