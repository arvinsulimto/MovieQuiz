package com.arvin.moviequiz.api

import com.arvin.moviequiz.models.Movies
import retrofit2.Response

interface ApiHandler {
    fun OnResponse(response: Movies?)
}