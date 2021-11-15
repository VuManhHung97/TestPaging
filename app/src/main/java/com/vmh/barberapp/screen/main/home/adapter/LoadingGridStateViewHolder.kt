package com.vmh.barberapp.screen.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.vmh.barberapp.R
import kotlinx.android.synthetic.main.loading_grid_item.view.*

class LoadingGridStateViewHolder(
    private val view: View
): RecyclerView.ViewHolder(view) {

    fun bind(loadState: LoadState) {
        view.progressBar.isVisible = loadState is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup): LoadingGridStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.loading_grid_item, parent, false)

            return LoadingGridStateViewHolder(
                view
            )
        }
    }
}
