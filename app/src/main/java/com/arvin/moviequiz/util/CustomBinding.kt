package com.arvin.moviequiz.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class CustomBinding {
    companion object {
        @JvmStatic
        @BindingAdapter("app:imageUrl")
        fun loadImage(view: ImageView, url: String) {
            Glide.with(view.context).load(url).into(view)
        }

        @JvmStatic
        @BindingAdapter("android:visibility")
        fun setVisibility(view:View,visible:Boolean){
            view.visibility = if(visible) View.VISIBLE else View.INVISIBLE
        }

    }
}