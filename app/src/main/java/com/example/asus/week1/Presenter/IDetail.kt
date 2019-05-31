package com.example.asus.week1.Presenter

import android.content.Context
import com.example.asus.week1.Model.Film
import com.example.asus.week1.Model.Trailer
import com.example.asus.week1.Model.Trailers

interface IDetail {
    interface View {
        fun setPresenter(presenter: Presenter)
        //fun show(film: Film)
        fun onResponse(trailers: List<Trailer>?)

        fun onResponse(films: MutableCollection<Film>?)

        fun onFailure()
    }

    interface Presenter {
        fun getTrailer(id:Int?)
        fun getRecommendations(id:Int?)
    }
}