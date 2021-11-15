package com.vmh.barberapp.screen.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.adrena.commerce.paging3.data.model.Movies
import com.vmh.barberapp.R
import kotlinx.android.synthetic.main.barber_layout_item.view.*

class MovieGridViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(movie: Movies.Movie) {
        with(movie) {
            view.poster.load(poster?.medium) {
                crossfade(true)
            }
        }
    }

    companion object {
        fun create(parent: ViewGroup): MovieGridViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_grid_item,  parent,false)

            return MovieGridViewHolder(
                view
            )
        }
    }
}
