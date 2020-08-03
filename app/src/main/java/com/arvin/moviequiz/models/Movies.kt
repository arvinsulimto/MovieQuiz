package com.arvin.moviequiz.models

import com.google.gson.annotations.SerializedName

class Movies {
    @SerializedName("results")
    var dataMovies:List<DataMovies>? = ArrayList<DataMovies>()
    var correctAnswerIdx:Int = 0
}