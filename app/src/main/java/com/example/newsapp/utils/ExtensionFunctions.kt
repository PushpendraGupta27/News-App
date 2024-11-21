package com.example.newsapp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun ImageView.loadImage(imageUrl: String?) {
    Glide.with(this.context)
        .load(imageUrl)
        .thumbnail(Glide.with(this.context).load(imageUrl))
        .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
        .into(this)
}