package com.example.asus.week1.Model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Films {

    @SerializedName("results")
    @Expose
    val results: MutableCollection<Film>? = null
    @SerializedName("page")
    @Expose
    val page: Int? = null
    @SerializedName("total_results")
    @Expose
    val totalResults: Int? = null
    @SerializedName("total_pages")
    @Expose
    val totalPages: Int? = null
}