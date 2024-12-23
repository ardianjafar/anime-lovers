package com.manyan.anime.core.utils

import android.widget.ImageView
import com.manyan.anime.core.R
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .centerCrop()
        .error(R.drawable.poster_placeholder)
        .placeholder(R.drawable.poster_placeholder)
        .into(this)
}