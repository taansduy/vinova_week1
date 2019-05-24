package com.example.asus.week1.Presenter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import com.example.asus.week1.Model.Film
import com.example.asus.week1.Model.Films
import com.example.asus.week1.Model.Trailer
import com.example.asus.week1.Model.Trailers
import com.example.asus.week1.View.Film_detail
import com.example.asus.week1.api.api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPresenter(val view: IDetail.View) : IDetail.Presenter {
    override fun showDetail(context: Context, film: Film) {
        val intent = Intent(context, Film_detail::class.java)
        intent.putExtra("Film", film);
        ContextCompat.startActivity(context, intent, null)
    }

    override fun getRecommendations(id: Int?) {
        api.createService().doGetRecommendations(id).enqueue(object : Callback<Films>{
            override fun onFailure(call: Call<Films>?, t: Throwable?) {
                println(t.toString())

            }

            override fun onResponse(call: Call<Films>, response: Response<Films>) {
                if (response.body() != null) {
                    view.onResponse(response.body()?.results)
                }
            }

        })
    }

    override fun getTrailer(id: Int?) {

        api.createService().doGetTrailer(id).enqueue(object : Callback<Trailers> {
            override fun onFailure(call: Call<Trailers>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<Trailers>?, response: Response<Trailers>) {
                if (response.body() != null) {
                    view.onResponse(response.body()?.results as List<Trailer>)
                }
            }
        })
    }
}