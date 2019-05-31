package com.example.asus.week1.Presenter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.widget.Toast
import com.example.asus.week1.Model.Film
import com.example.asus.week1.Model.Films
import com.example.asus.week1.View.Film_detail
import com.example.asus.week1.api.api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NowPlayingPresenter(val view: INowPlaying.View) : INowPlaying.Presenter{

    override fun refreshNowPlaying(page: Int) {
        api.createService().doGetListNowPlayingMovies(page).enqueue(object : Callback<Films>{
            override fun onFailure(call: Call<Films>?, t: Throwable?) {
                println(t.toString())

            }

            override fun onResponse(call: Call<Films>, response: Response<Films>) {
                if (response.body() != null) {
                    view.onResponse(response.body()?.results,0)
                }
            }

        })
    }

    override fun getNowPlaying(page: Int) {
        api.createService().doGetListNowPlayingMovies(page).enqueue(object : Callback<Films>{
            override fun onFailure(call: Call<Films>?, t: Throwable?) {
                println(t.toString())

            }

            override fun onResponse(call: Call<Films>, response: Response<Films>) {
                if (response.body() != null) {
                    view.onResponse(response.body()?.results,1)

                }
            }

        })
    }
}