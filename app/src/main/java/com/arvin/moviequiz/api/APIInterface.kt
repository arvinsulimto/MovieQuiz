package com.arvin.moviequiz.api

import com.arvin.moviequiz.models.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIInterface {
    @GET("3/discover/movie?")
    fun getMovies(@Query("api_key") apiKey:String): Call<Movies>
}