package com.example.asus.week1.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



class Trailers {

    @SerializedName("id")
    @Expose
    private var id: Int? = null
    @SerializedName("results")
    @Expose
    var results: List<Trailer>? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }
}
class Trailer {
    var id: String? = null
    var iso6391: String? = null
    var iso31661: String? = null
    var key: String? = null
    var name: String? = null
    var site: String? = null
    var size: Int? = null
    var type: String? = null
}