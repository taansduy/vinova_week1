package com.example.asus.week1.adapter

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.asus.week1.Model.Film
import com.example.asus.week1.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recommendation_film_layout.*
import android.R.attr.orientation
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.opengl.Visibility
import android.support.annotation.NonNull
import android.widget.ProgressBar
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.centerInside
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.asus.week1.R.id.progressBar
import com.example.asus.week1.utils.onItemClickListener
import kotlinx.android.synthetic.main.high_rate_film_item.*
import java.security.MessageDigest


class Recommendation_Film_Adapter(private val context: Context) : RecyclerView.Adapter<Recommendation_Film_Adapter.ViewHolder>() {
    override fun onBindViewHolder(viewHolder: Recommendation_Film_Adapter.ViewHolder, position: Int) {
        viewHolder.bind(films[position])
    }

    private var films :MutableCollection<Film> =ArrayList()
    var itemClick: onItemClickListener?= null
        inner class ViewHolder( override val containerView: View): RecyclerView.ViewHolder(containerView),LayoutContainer{

        fun bind(film: Film?) {
            containerView.setOnClickListener{
                itemClick!!.onItemClick(film!!)
            }
            var linkImage = "https://image.tmdb.org/t/p/"
            linkImage=linkImage+"w200"+film?.posterPath
            var obj=RoundedCorners(8)
            val options = RequestOptions().transform(obj)
                .centerInside()
                .error(R.drawable.no_image)
                .placeholder(R.drawable.loading).fitCenter()
            Glide.with(context?.applicationContext ?: return)
                .load(linkImage)
                .fitCenter()
                .apply(options)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img_poster)
            tv_name.text=film?.title
        }
    }





    fun setItemClickListener(clickListener: onItemClickListener ) {
        itemClick = clickListener;
    }
    /* override fun onBindViewHolder(viewHolder: FilmAdapter.ViewHolder, position : Int) {
         viewHolder.bind(films[position])
     }*/

    override fun onCreateViewHolder(parent : ViewGroup, viewType: Int): Recommendation_Film_Adapter.ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.recommendation_film_layout,
                    parent,
                    false
                )
            )
    }

    override fun getItemCount(): Int {
        return films?.size
    }

    fun addAll(mFilms:MutableCollection<Film>)
    {
        var lastSize=films.size
        films.addAll(mFilms)
        notifyItemRangeInserted(lastSize,mFilms.size)
        //notifyDataSetChanged()
    }
    fun updateSource(mFilms:MutableCollection<Film>)
    {
        films.clear()
        films.addAll(mFilms)
        notifyDataSetChanged()
    }


}

private operator fun <E> MutableCollection<E>.get(position: Int): E? {
    return this.elementAt(position)

}
