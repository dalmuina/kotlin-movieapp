package com.dalmuina.data.remote

object Endpoints {
    const val POPULAR_MOVIES = "/movie/popular"
    fun movieDetail(id:Int) = "/movie/$id"
}