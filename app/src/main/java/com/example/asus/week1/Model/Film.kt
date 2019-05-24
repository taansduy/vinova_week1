package com.example.asus.week1.Model;
import android.os.Parcelable
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose
import java.io.Serializable


public class Film: Serializable {


     val adult: Boolean? = null

    @SerializedName("backdrop_path")
    @Expose
     val backdropPath: String? = null

    @SerializedName("budget")
    @Expose
     val budget: Int? = null

    @SerializedName("homepage")
    @Expose
     val homepage: String? = null

    @SerializedName("id")
    @Expose
     val id: Int? = null

    @SerializedName("imdb_id")
    @Expose
     val imdbId: String? = null

    @SerializedName("original_language")
    @Expose
     val originalLanguage: String? = null

    @SerializedName("original_title")
    @Expose
     val originalTitle: String? = null

    @SerializedName("overview")
    @Expose
     val overview: String? = null

    @SerializedName("popularity")
    @Expose
     val popularity: Double? = null

    @SerializedName("poster_path")
    @Expose
     val posterPath: String? = null

    @SerializedName("release_date")
    @Expose
     val releaseDate: String? = null

    @SerializedName("runtime")
    @Expose
     val runtime: Int? = null

    @SerializedName("status")
    @Expose
     val status: String? = null
    
     val title: String? = null

    @SerializedName("video")
    @Expose
     val video: Boolean? = null

    @SerializedName("vote_average")
    @Expose
     val voteAverage: Double? = null

    @SerializedName("vote_count")
    @Expose
     val voteCount: Int? = null

}
