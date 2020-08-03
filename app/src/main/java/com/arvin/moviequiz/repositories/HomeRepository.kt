package com.arvin.moviequiz.repositories

import androidx.lifecycle.MutableLiveData
import com.arvin.moviequiz.api.APIClient
import com.arvin.moviequiz.api.ApiHandler
import com.arvin.moviequiz.models.Movies
import com.arvin.moviequiz.util.Const
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository {
    fun getMovies(handler: ApiHandler){
        APIClient().api().getMovies(Const.API_KEY).enqueue(object:
            Callback<Movies> {
            override fun onFailure(call: Call<Movies>, t: Throwable) {

            }
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if(response.isSuccessful){
                    handler.OnResponse(response.body())
                }

            }
        })

    }
}