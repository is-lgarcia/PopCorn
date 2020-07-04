package com.luisg.popcorn.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.luisg.popcorn.R
import com.luisg.popcorn.common.Constants
import com.luisg.popcorn.model.retrofit.response.Movie
import com.luisg.popcorn.model.retrofit.response.TopRatedMovie

class TopRatedAdapter(var mValues: List<TopRatedMovie>) : RecyclerView.Adapter<TopRatedAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener {
            val item = it.tag as TopRatedAdapter
        }
    }


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
        holder.txtGenre.text = "Ciencia Ficción"
        holder.txtAverege.text = item.vote_average.toString()
        holder.imageCover.load(Constants.IMAGE_BASE_URL + item.poster_path){
            crossfade(true)
            placeholder(R.drawable.ic_cine)
        }
    }

    fun setData(topRatedMovie: List<TopRatedMovie>){
        mValues = topRatedMovie
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imageCover: ImageView = itemView.findViewById(R.id.id_image_cover)
        val txtTitleMovie: TextView = itemView.findViewById(R.id.txtTitle)
        val txtGenre: TextView = itemView.findViewById(R.id.txt_genre)
        val txtAverege: TextView = itemView.findViewById(R.id.txt_ranking)

    }


}