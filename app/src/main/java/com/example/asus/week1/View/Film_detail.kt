package com.example.asus.week1.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Config
import com.example.asus.week1.BuildConfig
import com.example.asus.week1.Presenter.DetailPresenter
import com.example.asus.week1.Presenter.IDetail
import com.example.asus.week1.R
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_film_detail.*
import com.example.asus.week1.BuildConfig.YOUTUBE_API_KEY
import android.content.Intent
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.OrientationHelper
import android.view.View
import android.widget.Toolbar
import com.example.asus.week1.Model.Film
import com.example.asus.week1.Model.Trailer
import com.example.asus.week1.adapter.FilmAdapter
import com.example.asus.week1.adapter.Recommendation_Film_Adapter
import com.example.asus.week1.utils.onItemClickListener
import com.google.android.youtube.player.YouTubeBaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class Film_detail :  IDetail.View, YouTubeBaseActivity() {
    private var  myadapter: Recommendation_Film_Adapter?=null
    private var mLayoutManager: LinearLayoutManager? = null
    override fun onResponse(films: MutableCollection<Film>?) {
        if (films == null) return
        myadapter?.addAll(films)
        /*if(myadapter!!.itemCount < position) {
            rcv.scrollToPosition(myadapter!!.itemCount-1)
        }
        else
        {
            if(position!=0) rcv.scrollToPosition(position)
            pullRefresh.isRefreshing=false
        }*/

    }

    private var Flag=0
    override fun onResponse(trailers: List<Trailer>?) {
        if(trailers?.size==0) return
        var source=trailers?.get(0)?.key
        youTubePlayerInit=object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?, wasRestored: Boolean) {
                if(Flag==0) youTubePlayer?.cueVideo(source)
                else youTubePlayer?.loadVideo(source)
            }

            override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


        }
        youtube_view.initialize(BuildConfig.YOUTUBE_API_KEY, youTubePlayerInit);
    }

    override fun onFailure() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //private val RECOVERY_REQUEST = 1
    lateinit var youTubePlayerInit: YouTubePlayer.OnInitializedListener


    private var presenter: IDetail.Presenter

    init{
        presenter= DetailPresenter(this);

    }
    override fun setPresenter(presenter: IDetail.Presenter) {
        this.presenter=presenter;
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)
        var data:Film =getIntent().getSerializableExtra("Film") as Film
        tv_name.text=data.originalTitle
        tv_releaseDay.text="Released date: "+data.releaseDate
        tv_content.text=data.overview
        if(data.voteAverage!!>7) Flag=1 else Flag=0
        ratingBar.rating=data.voteAverage!!.toFloat()
        toolbar.setNavigationIcon(R.drawable.back_arrow_white)
        toolbar.setNavigationOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                this@Film_detail.onBackPressed()
            }

        })

        presenter.getTrailer(data.id)
        presenter.getRecommendations(data.id)
        myadapter = Recommendation_Film_Adapter(this)
        var itemClickListener=object: onItemClickListener {
            override fun onItemClick(item: Film) {
                val intent = Intent(this@Film_detail, Film_detail::class.java)
                intent.putExtra("Film", item);
                ContextCompat.startActivity(this@Film_detail, intent, null)
            }
        }
        myadapter?.setItemClickListener(itemClickListener)
        mLayoutManager = LinearLayoutManager(this,OrientationHelper.HORIZONTAL,false)
        rcv_Recommendations.adapter=this.myadapter
        rcv_Recommendations.layoutManager=mLayoutManager

    }
}

