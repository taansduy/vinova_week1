package com.example.asus.week1.api

import com.example.asus.week1.Model.Films
import com.example.asus.week1.Model.Trailers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDatabaseApi {
    @GET("movie/now_playing")
    fun doGetListNowPlayingMovies(@Query("page") page: Int = 1): Call<Films>
    @GET("movie/{movie_id}/videos")
    fun doGetTrailer(@Path("movie_id") movieId: Int?): Call<Trailers>
    @GET("movie/{movie_id}/recommendations")
    fun doGetRecommendations(@Path("movie_id") movieId: Int?): Call<Films>
}