package com.vmh.barberapp.utils

import android.view.View
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val views = SparseArrayCompat<View>()

    abstract fun bindData(data: T, listener: OnItemRecyclerViewClickListener<T>, position: Int)

    fun view() = itemView
}
