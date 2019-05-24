package com.example.asus.week1.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.asus.week1.Model.Film
import com.example.asus.week1.Presenter.INowPlaying
import com.example.asus.week1.Presenter.NowPlayingPresenter
import com.example.asus.week1.R
import com.example.asus.week1.adapter.FilmAdapter
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Parcelable
import android.sax.EndTextElementListener
import android.support.v7.widget.RecyclerView.LayoutManager
import android.view.MotionEvent
import com.example.asus.week1.utils.EndlessScrollListener
import com.example.asus.week1.utils.onItemClickListener
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator




class MainActivity : AppCompatActivity(),INowPlaying.View {

    private var presenter: INowPlaying.Presenter
    private var  myadapter: FilmAdapter?=null
    var state: Parcelable? = null
    var flag=false
    var position=0
    private var mLayoutManager: LinearLayoutManager? = null

    init{
        presenter=NowPlayingPresenter(this);
    }

    override fun setPresenter(presenter: INowPlaying.Presenter) {
        this.presenter=presenter;
    }

    override fun onResponse(films: MutableCollection<Film>?,type: Int) {
        if (films == null) return

        when(type)
        {
            0->{myadapter?.updateSource(films)
                pullRefresh.isRefreshing=false}
            1->{
                myadapter?.addAll(films)
                if(myadapter!!.itemCount < position) {
                 rcv.scrollToPosition(myadapter!!.itemCount-1)
                }
                else
                {
                    if(position!=0) rcv.scrollToPosition(position)
                    pullRefresh.isRefreshing=false
                }

            }
        }

    }
    override fun onFailure() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.getNowPlaying(1)
        myadapter = FilmAdapter(this)
        var itemClickListener=object: onItemClickListener {
            override fun onItemClick(item: Film) {
                presenter.showDetail(this@MainActivity,item)
            }
        }
        myadapter?.setItemClickListener(itemClickListener)
        mLayoutManager = LinearLayoutManager(this)
        rcv.adapter=this.myadapter
        rcv.layoutManager=mLayoutManager
        pullRefresh.isRefreshing=true
        pullRefresh.setOnRefreshListener {
            position=0
            presenter.refreshNowPlaying(1)
        }
        var scrollListener = object : EndlessScrollListener(mLayoutManager!!,1) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                presenter.getNowPlaying(page)
            }
        }
        rcv.addOnScrollListener(scrollListener)
        rcv.setItemAnimator(SlideInUpAnimator())

}

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("POSITION", mLayoutManager?.findFirstCompletelyVisibleItemPosition()!!)
        super.onSaveInstanceState(outState);

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        position= savedInstanceState?.getInt("POSITION")!!
        super.onRestoreInstanceState(savedInstanceState)
    }
}
