package com.arvin.moviequiz.models

import com.google.gson.annotations.SerializedName
import retrofit2.http.Query

class DataMovies {
    @SerializedName("title")
    val titleMovie:String? = null
    @SerializedName("poster_path")
    val posterMovie:String? = null
}