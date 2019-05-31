package com.example.asus.week1.Presenter

import android.content.Context
import com.example.asus.week1.Model.Film

interface INowPlaying {
    interface View {
        fun setPresenter(presenter: Presenter)

        fun onResponse(movies: MutableCollection<Film>?,type: Int)

        fun onFailure()
    }

    interface Presenter {
        fun getNowPlaying(page: Int)
        fun refreshNowPlaying(page: Int)
    }
}