package com.madudka.countries.utils.glide

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.madudka.countries.R

fun ImageView.loadImage(context: Context, link: String){
    GlideApp.with(context)
        .load(link)
        .placeholder(R.drawable.earth_flag)
        .skipMemoryCache(true)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}